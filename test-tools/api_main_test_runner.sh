#!/bin/bash
# CREATE ENTRIES

# THIS SCRIPT WILL RUN THE OTHER THREE


BASE_URL=$1
RUNTIME=$2

# CREATE
./create_entry_test.sh $BASE_URL $RUNTIME

# EDIT
./edit_entry_test.sh $BASE_URL $RUNTIME

# DELETE
./delete_entries.sh $BASE_URL $RUNTIME


