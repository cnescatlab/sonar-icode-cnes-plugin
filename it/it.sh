#!/bin/bash

usage(){
    echo -e "\nUsage: $0 [sSh] \n"
    echo "-h : Display help"
    echo "-s [SONAR-SCANNER] : Take Sonar-scanner tag image from https://hub.docker.com/r/sonarsource/sonar-scanner-cli". Default is the latest tag
    echo "-S [SONARQUBE] : Take SonarQube tag image from https://hub.docker.com/_/sonarqube". Default is the community tag
}

OPTSTRING=":s:S:h"

while getopts ${OPTSTRING} opt; do
  case ${opt} in
    s)
      export SCANNER_VERSION=$OPTARG
      ;;
    S)
      export SONARQUBE_VERSION=$OPTARG
      ;;
    h)
      usage
      exit 0
      ;;
    :)
      echo "Option -${OPTARG} requires an argument."
      usage
      exit 1
      ;;
    ?)
      echo "Invalid option: -${OPTARG}."
      usage
      exit 1
      ;;
  esac
done


export SCRIPT_DIR=`dirname $0`

# Clean-up if needed
echo "Cleanup..."
docker compose -f $SCRIPT_DIR/docker-compose.yml down

# Start containers
echo "Starting SonarQube..."
docker compose -f $SCRIPT_DIR/docker-compose.yml up --quiet-pull -d sonarqube
CONTAINER_NAME=$(docker ps --format "{{.Names}}" | grep 'it-sonarqube-1.*' | head -1)
# Wait for SonarQube to be up
grep -q "SonarQube is operational" <(docker logs --follow --tail 0 $CONTAINER_NAME)
echo "SonarQube started!"

# Copy the plugins
MAVEN_VERSION=$(grep '<version>' $SCRIPT_DIR/../pom.xml | head -1 | sed 's/<\/\?version>//g'| awk '{print $1}')
echo "Installing the plugin Icode version $MAVEN_VERSION"
docker cp $SCRIPT_DIR/../target/sonar-icode-cnes-plugin-$MAVEN_VERSION.jar $CONTAINER_NAME:/opt/sonarqube/extensions/plugins
# Restart SonarQube
docker compose -f $SCRIPT_DIR/docker-compose.yml restart sonarqube
# Wait for SonarQube to be up
grep -q "SonarQube is operational" <(docker logs --follow --tail 0 $CONTAINER_NAME)
# Check plug-in installation
docker exec -u root $CONTAINER_NAME bash -c "if grep -q Alpine /etc/issue; then apk update && apk add -q curl; fi"
docker exec -u root $CONTAINER_NAME bash -c "if grep -q Ubuntu /etc/issue; then apt-get update && apt-get install -y curl; fi"
if ! docker exec $CONTAINER_NAME curl -su admin:admin http://localhost:9000/api/plugins/installed | python -c '
import sys
import json
plugins_count = 0
plugins_expected = ["icode"]
data = json.loads(sys.stdin.read())
if "plugins" in data:
    for plugin in data["plugins"]:
      if plugin["key"] in plugins_expected:
        plugins_count += 1
if plugins_count == len(plugins_expected):
  sys.exit(0)
else:
  sys.exit(1)
'
then
    echo "Plugin not installed" >&2
    exit 1
fi
echo "Plugin successfully installed!"

# Audit code
echo "Audit test scripts..."
docker compose -f $SCRIPT_DIR/docker-compose.yml up --quiet-pull --build --exit-code-from auditor auditor
AUDIT_STATUS=$?

# Delete containers
echo "Cleanup..."
docker compose -f $SCRIPT_DIR/docker-compose.yml down

exit $AUDIT_STATUS
