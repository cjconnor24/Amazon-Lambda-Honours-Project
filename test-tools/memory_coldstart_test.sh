#!/bin/bash

#!/bin/bash

RUNTIME=$1
MEM_SIZE=$2

RESULTS_FILENAME="memory_timeout_coldstart_results_${RUNTIME}_${MEM_SIZE}_$(date +%d-%m-%Y_%H%M).txt"


echo $RESULTS_FILENAME

folder="$1-memory"

echo "Switching folder to $folder"

cd ../$folder

COUNT=0

until [ $COUNT -gt 100 ]; do
    
    let COUNT=COUNT+1
    echo "sls invoke -l -f memTest$MEM_SIZE | tee $RESULTS_FILENAME"
    echo Sleeping 30 minutes

    # sleep for 30 minutes
    sleep $[30 * 60]
done
