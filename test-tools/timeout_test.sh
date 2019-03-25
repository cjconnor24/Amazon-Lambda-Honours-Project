#!/bin/bash

# VARIABLES
BASE_URL=$1
DATA=$(jo text="Todo created at $(date)" checked=false)
RUNTIME=$2
HTTP_METHOD=POST
RESULTS_FILENAME="coldstart_timeout_results_${RUNTIME}_${HTTP_METHOD}_$(date +%d-%m-%Y_%H%M).txt"


SLEEP_TIMER=5
SLEEP_INTERVAL=5

COUNT=0
# bash until loop
until [ $SLEEP_TIMER -gt 240 ]; do
# while [ 1 ]; do
    
    curl -X $HTTP_METHOD -d "$DATA" -o /dev/null -s $BASE_URL -w "%{url_effective}\t${RUNTIME}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a $RESULTS_FILENAME
    let COUNT=COUNT+1
    
    
    echo "$RUNTIME: Sleeping for $SLEEP_TIMER minutes $(date +%H:%M:%S)"
    sleep $[$SLEEP_TIMER * 60]
    let SLEEP_TIMER=SLEEP_TIMER+SLEEP_INTERVAL
done
