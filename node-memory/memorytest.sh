#!/bin/bash

sls invoke --region eu-west-2 -l -f hello128 | tee memory_results.txt
sls invoke --region eu-west-2 -l -f hello512 | tee memory_results.txt
sls invoke --region eu-west-2 -l -f hello1024 | tee memory_results.txt

