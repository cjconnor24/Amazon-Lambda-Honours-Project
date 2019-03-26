#!/bin/bash

RUNTIME=$1
MEM_SIZE=$2

RESULTS_FILENAME="memory_regular_results_${RUNTIME}_${MEM_SIZE}_$(date +%d-%m-%Y_%H%M).txt"


echo $RESULTS_FILENAME

folder="$1-memory"

echo "Switching folder to $folder"

cd ../$folder

COUNT=0

until [ $COUNT -gt 500 ]; do
    
    let COUNT=COUNT+1

    # INVOKE THE FUNCTION
    sls invoke -l -f memTest$MEM_SIZE | tee -a $RESULTS_FILENAME
    date "+%d/%m/%Y %H:%M:%S" >> $RESULTS_FILENAME
    
    sleep 1s
done

say "$RUNTIME test complete $2 MEGABYTES"
