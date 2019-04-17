#!/bin/sh 

# CHRIS CONNOR
# S1715477
# CCONNO208@CALEDONIAN.AC.UK

# NOT PART OF PROJECT PER SE, JUST HELPS ME LOCATE DUPLICATE FILES :)

dirname=./

find $dirname -type f | sed 's_.*/__' | sort|  uniq -d| 
while read fileName
do
find $dirname -type f | grep "$fileName"
done
