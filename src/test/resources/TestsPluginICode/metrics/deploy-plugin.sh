#!/bin/bash

# Nom du plugin.
PLUGIN_NAME="sonar-icode-plugin-0.0.1-SNAPSHOT.jar"

# Nom du repertoire partager ou aller chercher le plugin.
PLUGIN_SHARE_PATH="/mnt/Share/"

# Repertoire de destination du plugin à installer.
PLUGIN_DESTINATION_PATH="/opt/sonar/extensions/plugins/"

# Fichier de log pour SONAR web server.
SONAR_LOG_PATH="/opt/sonar/logs/web.log"

# Répertoire à tester par SONAR-SCANNER.
PROJECT_TEST_PATH="."

# Chaine à trouver dans les log.
STRING_TO_FOUND="WebServer is operational"

# Commande à exécuter pour savoir si le serveur web de SONAR est oppérationel.
COMMAND="cat $SONAR_LOG_PATH" # |grep -s \"$STRING_TO_FOUND\""

# Flag de recherche.
isSearching=0

# Font Color
# To be defined...
COLOR_RED_BOLD='\e[1m'
COLOR_RED='\e[31m'
COLOR_RESET='\e[0m'

function Add_New_Plugin
{
        echo -e "\e[35m\e[1mADD NEW PLUGIN\e[39m"
        echo "=============="
        if [ -f $PLUGIN_SHARE_PATH$PLUGIN_NAME ]; then
                echo -e "  > \e[93mMoving \e[1m$PLUGIN_NAME \e[21mto SONAR plugins repository (\e[1m$PLUGIN_DESTINATION_PATH\e[21m).\e[39m"
                mv $PLUGIN_SHARE_PATH$PLUGIN_NAME $PLUGIN_DESTINATION_PATH
                echo -e "  > \e[32mPlugin \e[1m$PLUGIN_NAME\e[21m moved.\e[39m"
                echo " "
        elif [ ! -f $PLUGIN_SHARE_PATH$PLUGIN_NAME ]; then
                echo -e "  > \e[31mPlugin to move not found. (\e[1m$PLUGIN_SHARE_PATH$PLUGIN_NAME\e[21m)\e[39m"
		echo " "
                exit
        fi
}

function Clean_Sonar_Web_Log
{
        echo -e "\e[35m\e[1mCLEAN SONAR WEB LOG\e[39m"
        echo "==================="
        echo -e "  > \e[93mSONAR Web log is cleaning.\e[39m"
        echo "" > $SONAR_LOG_PATH
        echo -e "  > \e[32mSONAR Web log is empty.\e[39m"
	echo " "
}


function Restart_Sonar_Server
{
        echo -e "\e[35m\e[1mRESTART SONAR\e[39m"
        echo "============="
        echo -e "  > \e[93mService SONAR is restarting.\e[39m"

        sonar restart

        echo -e "  > \e[96mPlease, wait. SONAR Web Server is not again started...\e[39m"

        while [ "$isSearching" -eq 0 ]
        do
                outptString=$($COMMAND)
                lenght=${#outptString}
                if [ "$lenght" -gt 0 ]; then
                        if [[ $outptString =~ $STRING_TO_FOUND  ]]; then
                                isSearching=$lenght
                                break
                        fi
                fi

                printf "."

                sleep 1s
        done

	echo " "
        echo -e "  > \e[32mSONAR Web Server is started.\e[39m"
        echo " "
}

function Launch_Sonar_Scanner
{
        cd $PROJECT_TEST_PATH

        echo -e "\e[35m\e[1mLAUNCH SONAR SCANNER\e[39m"
        echo "===================="
        echo -e "  > \e[93mRunning a scanner into project METRICS.\e[39m"

        sonar-scanner

	echo " "
        echo -e "  > \e[32mSonar scanner is done.\e[39m"
        echo " "
}

function main
{
        Add_New_Plugin
        Clean_Sonar_Web_Log
        Restart_Sonar_Server
        Launch_Sonar_Scanner
        exit
}

main
