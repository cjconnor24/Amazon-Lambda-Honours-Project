#!/bin/bash
# CREATE ENTRIES

# THIS SCRIPT WILL CREATE 200 ENTRIES AT AN INTERVAL OF 500ms


# VARIABLES
# BASE_URL="https://3u5d6296vf.execute-api.eu-west-2.amazonaws.com/node-dev/todos"
BASE_URL=$1
DATA=$(jo text="Todo created at $(date)" checked=false)
RUNTIME=$2
HTTP_METHOD=POST
RESULTS_FILENAME="results_${RUNTIME}_${HTTP_METHOD}_$(date +%d-%m-%Y_%H%M).txt"

# RUNTIME_API=${BASE_URL#*aws.com/}
# echo $TEMP_URL

echo $RESULTS_FILENAME

# -o /dev/null

MINUTES=30

COUNT=0
# bash until loop
until [ $COUNT -gt 500 ]; do

    declare -a arr=("512" "1024" "2048")
    
    for i in "${arr[@]}"
    do
        curl -X $HTTP_METHOD -d "$DATA" -o /dev/null -s $BASE_URL$i -w "%{url_effective}\t${RUNTIME}\t${i}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a $RESULTS_FILENAME
        let COUNT=COUNT+1

        # SLEEP FOR 10s before running the next memory size
        sleep 10s

        echo "$RUNTIME $i: Sleeping for $MINUTES minutes $(date +%H:%M:%S)"
    done

    # sleep $COUNT
    sleep $[$MINUTES * 60]
    # sleep 1s
done
