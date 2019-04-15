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

until [ $COUNT -gt 50 ]; do
    
    # curl -X $HTTP_METHOD -d "$DATA" -o /dev/null -s $BASE_URL -w "%{url_effective}\t${RUNTIME}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a $RESULTS_FILENAME
    let COUNT=COUNT+1
    echo "sls invoke -l -f memTest$MEM_SIZE | tee $RESULTS_FILENAME"
    echo Sleeping 30 minutes
    # sleep $COUNT
    sleep $[30 * 60]
done


#sls invoke --region eu-west-2 -l -f hello128 | tee memory_results.txt
#sls invoke --region eu-west-2 -l -f hello512 | tee memory_results.txt
#sls invoke --region eu-west-2 -l -f hello1024 | tee memory_results.txt

