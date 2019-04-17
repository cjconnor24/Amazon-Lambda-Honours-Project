#!/bin/bash


# CHRIS CONNOR
# S1715477
# CCONNO208@CALEDONIAN.AC.UK

# REMOVE APIS

# THIS SCRIPT WILL REMOVE ALL THE APIS FROM LIVE
# DEPLOYMENT AUTOMATICALL WITHOUT HAVING TO CALL THEM
# INDIVIDUALLY VIA THE SERVERLESS FRAMEWORK


## declare an array variable
declare -a RUNTIME=("java" "node" "python3")

## now loop through the above array
for i in "${RUNTIME[@]}"
do
   folder="$i-memory"
   
   cd $folder
   
   echo "REMOVING $i API"
   echo ""
   pwd
   echo ""
   echo "####################"

   # REMOVE THE FUNCTIONS USING THE SERVERLESS FRAMEWORK
   sls remove
   

   # RETURN TO CALLING DIRECTORY
   cd ../
   
done

# OUTPUT THE CONSOLE TO UPDATE THE USER
echo "***** MEMORY TESTS NOW HAVE BEEN REMOVED *****"
