#!/bin/bash

## declare an array variable
declare -a arr=("java" "node" "python3")

## now loop through the above array
for i in "${arr[@]}"
do
   folder="$i-memory"
   
   cd $folder
   
   echo "Now deploying $i API"
   echo ""
   pwd
   echo ""
   echo "####################"

   sls deploy
   

   cd ../
   # or do whatever with individual element of the array
done

echo ALL MEMORY FUNCTIONS HAVE BEEN DEPLOYED

# You can access them using echo "${arr[0]}", "${arr[1]}" also
