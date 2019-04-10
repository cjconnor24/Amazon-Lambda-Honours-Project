#!/bin/bash

# VARIABLES
BASE_URL=$1
DATA=$(jo text="Todo created at $(date)" checked=false)
RUNTIME=$2
HTTP_METHOD=DELETE
RESULTS_FILENAME="results_${RUNTIME}_${HTTP_METHOD}_$(date +%d-%m-%Y_%H%M).txt"

echo "RUNNING TEST USING ${RUNTIME} RUNTIME AND ${HTTP_METHOD} METHOD"

ids_filename="${RUNTIME}_ids_$(date +%d-%m-%Y_%H%M).txt"


curl -s $BASE_URL | jq '.[] | .id' > "${ids_filename}"


for api in `cat ${ids_filename}`
do

    # STRIP THE QUOTATION MARKS
    todo_id="${api%\"}"
    todo_id="${todo_id#\"}"
    
    curl -X $HTTP_METHOD -o /dev/null -s "${BASE_URL}/${todo_id}" -w "%{url_effective}\t${RUNTIME}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a $RESULTS_FILENAME
    sleep 1s
    
done

rm "${ids_filename}"
echo "Delete completed"