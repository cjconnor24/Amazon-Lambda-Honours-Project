#!/bin/bash
# CREATE ENTRIES

# THIS SCRIPT WILL CREATE 200 ENTRIES AT AN INTERVAL OF 500ms


# VARIABLES
# BASE_URL="https://3u5d6296vf.execute-api.eu-west-2.amazonaws.com/node-dev/todos"
BASE_URL=$1
DATA=$(jo text="Todo UPDATED at $(date)" checked=true)
RUNTIME=$2
HTTP_METHOD=PUT
RESULTS_FILENAME="results_${RUNTIME}_${HTTP_METHOD}_$(date +%d-%m-%Y_%H%M).txt"

echo "RUNNING TEST USING ${RUNTIME} RUNTIME AND ${HTTP_METHOD} METHOD"

ids_filename="${RUNTIME}_ids_$(date +%d-%m-%Y_%H%M).txt"


curl -s $BASE_URL | jq '.[] | .id' > "${ids_filename}"


for api in `cat ${ids_filename}`
do

    # STRIP THE QUOTATION MARKS
    todo_id="${api%\"}"
    todo_id="${todo_id#\"}"
    
    curl -X $HTTP_METHOD -d "$DATA" -o /dev/null -s "${BASE_URL}/${todo_id}" -w "%{url_effective}\t${RUNTIME}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a $RESULTS_FILENAME
    sleep 0.5
    
done

rm "${ids_filename}"
echo "UPDATE completed"