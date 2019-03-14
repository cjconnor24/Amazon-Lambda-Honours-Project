'use strict';

const AWS = require('aws-sdk');
const dynamoDb = new AWS.DynamoDB.DocumentClient();

module.exports.update = (event, context, callback) => {

    const timestamp = new Date().getTime();
    const data = JSON.parse(event.body);

    // CHECK THAT ONE OR THE OTHER VALUES IS THERE
    if (typeof data.text !== 'string' || typeof data.checked !== 'boolean') {

        console.error('Validation Failed');

        callback(null, {
            statusCode: 400,
            headers: { 'Content-Type': 'text/plain' },
            body: 'Couldn\'t update the todo item.',
        });
        return;

    }

    // BUILD PARAMS FOR UPDATING IN DYNAMO
    const params = {
        TableName: process.env.DYNAMODB_TABLE,
        Key: {
            id: event.pathParameters.id,
        },
        ExpressionAttributeNames: {
            '#todo_text': 'text',
        },
        ExpressionAttributeValues: {
            ':text': data.text,
            ':checked': data.checked,
            ':updatedAt': timestamp,
        },
        UpdateExpression: 'SET #todo_text = :text, checked = :checked, updatedAt = :updatedAt',
        ReturnValues: 'ALL_NEW',
    };

    // TRIGGER THE ACTUAL UPDATE IN DYNAMO
    dynamoDb.update(params, (error, result) => {

        // CHECK FOR ERRORS
        if (error) {

            console.error(error);
            callback(null, {
                statusCode: error.statusCode || 501,
                headers: { 'Content-Type': 'text/plain' },
                body: 'Couldn\'t fetch the todo item.',
            });
            return;

        }

        // CREATE A RESPONSE TO SEND BACK
        const response = {
            statusCode: 200,
            body: JSON.stringify(result.Attributes),
        };
        callback(null, response);
    });
};
