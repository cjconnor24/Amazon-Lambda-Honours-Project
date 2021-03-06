#!/bin/bash

# CHRIS CONNOR
# S1715477
# CCONNO208@CALEDONIAN.AC.UK

# DEPLOY APIS

# THIS SCRIPT WILL DEPLOY ALL THREE APIS AT ONCE WITHOUT
# HAVING TO DEPLOY EACH ONE MANUALLY WITH THE SERVERLESS FRAMEWORK

# CREATE AN ARRAY WITH THE THREE FOLDERS
declare -a API_FOLDERS=("java" "node" "python3")

# LOOP THROUGH EACH OF THE FOLDERS
for i in "${API_FOLDERS[@]}"
do
   # CONCATENATE THE FULL FOLDER WITH -API
   folder="$i-api"
   
   # CHANGE DIRECTORY TO THIS FOLDER
   cd $folder
   
   # UPDATE CONSOLE TO KEEP USER AWARE OF PROGRESS
   echo "Now deploying $i API"
   echo ""
   pwd
   echo ""
   echo "####################"

   # CALL THE DEPLOY FUNCTION IN THE SERVERLESS FRAMEWORK
   sls deploy

   # RETURN TO THE MAIN FOLDER
   cd ../
   
done
