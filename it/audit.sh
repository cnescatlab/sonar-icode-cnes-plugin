#!/bin/sh -e

# ----------------------------------
# Colors
# ----------------------------------
NOCOLOR='\033[0m'
RED='\033[0;31m'
GREEN='\033[0;32m'
ORANGE='\033[0;33m'

# Function to get value from a property file
# arg 1 = the property
# arg 2 = the file path
function prop {
    grep "${1}" ${2} | cut -d'=' -f2
}

# Configure sonar-scanner
export SONAR_HOST_URL="http://sonarqube:9000"
export SONAR_ADMIN_LOGIN="admin"
export SONAR_ADMIN_PWD="admin"

# Generate Analysis token
echo "Generating analysis token..."
# Use an UUID for token name. It's useful to launch the audit several time on the same SonarQube execution
uuid=$(cat /proc/sys/kernel/random/uuid)
export SONAR_TOKEN=$(curl -su "$SONAR_ADMIN_LOGIN:$SONAR_ADMIN_PWD" -XPOST "$SONAR_HOST_URL/api/user_tokens/generate?name=$uuid&type=GLOBAL_ANALYSIS_TOKEN" | jq -r '.token')
echo $SONAR_TOKEN
# Audit code
echo "Launching scanner..."
cd /usr/src/myapp/it
sonar-scanner -Dsonar.log.level=DEBUG -Dsonar.verbose=true -Dsonar.qualitygate.wait 2>&1 | tee /tmp/scanner.log

if [ $? -ne 0 ]
then
    echo "${RED}Error scanning Shell scripts${NOCOLOR}" >&2
    exit 1
fi

# Check for warnings
if grep -q "^WARN: " /tmp/scanner.log
then
    echo -e "${ORANGE}Warnings found ${NOCOLOR}" >&2
    exit 1
fi

# Sleep a little because SonarQube needs some time to ingest the audit results
sleep 10

export SONAR_PROJECT_KEY=$(prop 'sonar.projectKey' sonar-project.properties)
echo "SONAR_PROJECT_KEY: $SONAR_PROJECT_KEY"

# Check audit result
echo "Checking result..."
python3 << EOF
from __future__ import print_function
import requests
import sys

r = requests.get('http://sonarqube:9000/api/issues/search?componentKeys=$SONAR_PROJECT_KEY:src/clanhb.f&statuses=OPEN', auth=('$SONAR_ADMIN_LOGIN', '$SONAR_ADMIN_PWD'))
if r.status_code != 200:
    print('Invalid server response: ' + str(r.status_code), file=sys.stderr)
    sys.exit(1)

data = r.json()

issues = 0
if 'f77-rules' in data['issues'][0]['rule'] and data['issues'][0]['line'] == 1:
    issues += 1

if data['total'] != 100:
    print('Wrong total number of issues: ' + str(data['total']), file=sys.stderr)
    sys.exit(1)
else:
    print('Validation Fortran 77 OK. Issues found: ' + str(data['total']), file=sys.stdout)

r = requests.get('http://sonarqube:9000/api/issues/search?componentKeys=$SONAR_PROJECT_KEY:src/clanhb.f90&statuses=OPEN', auth=('$SONAR_ADMIN_LOGIN', '$SONAR_ADMIN_PWD'))
if r.status_code != 200:
    print('Invalid server response: ' + str(r.status_code), file=sys.stderr)
    sys.exit(1)

data = r.json()


if 'f90-rules' in data['issues'][0]['rule'] and data['issues'][0]['line'] == 1:
    issues += 1

if data['total'] != 197:
    print('Wrong total number of issues: ' + str(data['total']), file=sys.stderr)
    sys.exit(1)
else:
    print('Validation Fortran 90 OK. Issues found: ' + str(data['total']), file=sys.stdout)


sys.exit(0 if issues == 2 else 1)
EOF
