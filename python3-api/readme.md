# Python API Results

Below is a brief summary of the test results for the Python API


# API Gateway Coldstart Tests
These were carried out using cURL and bash to send data to the API and measure the duration. Results were varied given the local area connection - full details can be read in the report.

| Average | Median | Min   | Max    | Std Deviation | 99p    | 
|---------|--------|-------|--------|---------------|--------| 
| 998ms   | 924ms  | 837ms | 1926ms | 204ms         | 1761ms | 

# Direct Invocation Coldstart Tests
There were carried out again using bash but this time directly calling the functions via the Serverless Framework

These results show the cold start times across each memory tier.

| MEMORY ALLOCATION   | AVERAGE | MEDIAN | MIN  | MAX  | STDEV | 99p |
|---------|--------|------|------|-------|-----|------| 
| 512 MB  | 84ms   | 85ms | 69ms | 93ms  | 6ms | 92ms | 
| 1024 MB | 48ms   | 48ms | 41ms | 55ms  | 4ms | 55ms | 
| 2048 MB | 43ms   | 43ms | 37ms | 48ms  | 3ms | 48ms | 


# Director Invocation Warm Start Tests

These results show the normal warm execution times across memory tiers.

| Memory Allocation | AVERAGE | MEDIAN | MIN  | MAX  | STDEV | 99p  | 
|-------------------|---------|--------|------|------|-------|------| 
| 512 MB            | 15ms    | 12ms   | 8ms  | 81ms | 9ms   | 50ms | 
| 1024 MB           | 12ms    | 9ms    | 8ms  | 45ms | 5ms   | 25ms | 
| 2048 MB           | 11ms    | 11ms   | 10ms | 46ms | 3ms   | 24ms | 


