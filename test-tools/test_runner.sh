#!/bin/bash

BASE_URL="https://6nt1o5nz31.execute-api.eu-west-2.amazonaws.com/node-dev/todos"

DATA=$(jo text="Todo created at $(date)" checked=false)
HTTP_METHOD=POST

# -o /dev/null


COUNT=0
# bash until loop
until [ $COUNT -gt 5 ]; do

curl -X $HTTP_METHOD -d "$DATA" -o /dev/null -s $BASE_URL -w "%{url_effective}\t%{http_code}\t%{time_pretransfer}\t%{time_starttransfer}\t%{time_total}\t$(echo $HTTP_METHOD)\t$(date)\n" | tee -a results.txt
        let COUNT=COUNT+1
        echo Sleeping $COUNT
        sleep $COUNT
done 
