# Node API Results

Below is a brief summary of the test results for the Node API


# API Gateway Coldstart Tests
These were carried out using cURL and bash to send data to the API and measure the duration. Results were varied given the local area connection - full details can be read in the report.

TABLE HERE

# Direct Invocation Coldstart Tests
There were carried out again using bash but this time directly calling the functions via the Serverless Framework

These results show the cold start times across each memory tier.

| Memory Allocation | AVERAGE | MEDIAN | MIN   | MAX   | STDEV | 99p   | 
|-------------------|---------|--------|-------|-------|-------|-------| 
| 512 MB            | 184ms   | 185ms  | 168ms | 205ms | 10ms  | 203ms | 
| 1024 MB           | 95ms    | 95ms   | 78ms  | 114ms | 9ms   | 114ms | 
| 2048 MB           | 66ms    | 66ms   | 60ms  | 78ms  | 4ms   | 76ms  | 


# Director Invocation Warm Start Tests
These results show the normal warm execution times across memory tiers.


| Memory Allocation | AVERAGE | MEDIAN | MIN  | MAX   | STDEV | 99p  | 
|-------------------|---------|--------|------|-------|-------|------| 
| 512 MB            | 29ms    | 27ms   | 12ms | 199ms | 16ms  | 65ms | 
| 1024 MB           | 23ms    | 23ms   | 7ms  | 90ms  | 9ms   | 46ms | 
| 2048 MB           | 22ms    | 21ms   | 7ms  | 73ms  | 8ms   | 39ms | 

