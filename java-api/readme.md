# Java API Results

Below is a brief summary of the test results for the Java API


# API Gateway Coldstart Tests
These were carried out using cURL and bash to send data to the API and measure the duration. Results were varied given the local area connection - full details can be read in the report.

| Average | Median | Min    | Max    | Std Deviation | 99p    | 
|---------|--------|--------|--------|---------------|--------| 
| 5136ms  | 5068ms | 4208ms | 6766ms | 417ms         | 6129ms | 


# Direct Invocation Coldstart Tests
There were carried out again using bash but this time directly calling the functions via the Serverless Framework

These results show the cold start times across each memory tier.

| Memory Allocation | AVERAGE | MEDIAN | MIN    | MAX    | STDEV | 99p    | 
|-------------------|---------|--------|--------|--------|-------|--------| 
| 512 MB            | na      | na     | na     | na     | na    | na     | 
| 1024 MB           | 4387ms  | 4424ms | 4023ms | 4620ms | 155ms | 4608ms | 
| 2048 MB           | 2161ms  | 2152ms | 1826ms | 2516ms | 112ms | 2498ms | 


# Director Invocation Warm Start Tests

These results show the normal warm execution times across memory tiers.

| Memory Allocation | AVERAGE | MEDIAN | MIN  | MAX  | STDEV | 99p  | 
|-------------------|---------|--------|------|------|-------|------| 
| 512 MB            | na      | na     | na   | na   | na    | na   | 
| 1024 MB           | 18ms    | 14ms   | 9ms  | 68ms | 10ms  | 45ms | 
| 2048 MB           | 14ms    | 12ms   | 10ms | 39ms | 5ms   | 30ms | 
