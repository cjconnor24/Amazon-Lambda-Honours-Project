import json
import datetime

def memTest(event, context):
    ts = datetime.datetime.now().timestamp()
    body = {
        "timestamp" : ts,
        "message": "Python Lambda Response. Memory Test. Function executed successfully!",
        "input": event
    }

    response = {
        "statusCode": 200,
        "body": json.dumps(body)
    }

    return response