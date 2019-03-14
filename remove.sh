#!/bin/bash

## declare an array variable
declare -a arr=("java" "node" "python3")

## now loop through the above array
for i in "${arr[@]}"
do
   folder="$i-api"
   
   cd $folder
   
   echo "REMOVING $i API"
   echo ""
   pwd
   echo ""
   echo "####################"

   sls remove
   

   cd ../
   # or do whatever with individual element of the array
done

# You can access them using echo "${arr[0]}", "${arr[1]}" also
echo "***** APIS NOW HAVE BEEN REMOVED *****"
