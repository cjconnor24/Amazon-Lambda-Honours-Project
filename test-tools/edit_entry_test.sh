#!/bin/bash
# CREATE ENTRIES

# THIS SCRIPT WILL CREATE 200 ENTRIES AT AN INTERVAL OF 500ms


# VARIABLES
BASE_URL=$1
RUNTIME=$2
HTTP_METHOD=PUT

# DUMMY JSON DATA TO SEND TO API
DATA=$(jo text="Todo UPDATED at $(date)" checked=true)

# FILE TO STORE RESULTS
RESULTS_FILENAME="results_${RUNTIME}_${HTTP_METHOD}_$(date +%d-%m-%Y_%H%M).txt"

echo "RUNNING TEST USING ${RUNTIME} RUNTIME AND ${HTTP_METHOD} METHOD"

# TEMP FILE TO STORE THE IDS
ids_filename="${RUNTIME}_ids_$(date +%d-%m-%Y_%H%M).txt"


curl -s $BASE_URL | jq '.[] | .id' > "${ids_filename}"

# LOOP THROUGH EACH OF THE IDS
for api in `cat ${ids_filename}`
do

    # STRIP THE QUOTATION MARKS
    todo_id="${api%\"}"
    todo_id="${todo_id#\"}"
    
    # UPDATE THE RECORD
    curl -X $HTTP_METHOD -d "$DATA" -o /dev/null -s "${BASE_URL}/${todo_id}" -w "%{url_effective}\t${RUNTIME}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a $RESULTS_FILENAME
    sleep 0.5
    
done

# REMOVE THE TEMP FILE
rm "${ids_filename}"
echo "UPDATE completed"