'use strict';

const AWS = require('aws-sdk'); // eslint-disable-line import/no-extraneous-dependencies

const dynamoDb = new AWS.DynamoDB.DocumentClient();

module.exports.get = (event, context, callback) => {

    const todoID = event.pathParameters.id;

    // TODO: VALIDATE ID
    // MAKE SURE THERE IS A ID AND IT ISN"T BLANK
    // if (typeof todoID !== 'string') {
    //     console.error('Validation Failed');
    //     callback(null, {
    //         statusCode: 400,
    //         headers: { 'Content-Type': 'text/plain' },
    //         body: 'Couldn\'t create the todo item.',
    //     });
    //     return;
    // }

    // CREATE PARAMS TO SEARCH THE DB
    const params = {
        TableName: process.env.TODO_TABLE_NAME,
        Key: {
            id: event.pathParameters.id,
        },
    };

    // GET THE TODO ITEM FROM DYNAMO
    dynamoDb.get(params, (error, result) => {

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

        // CREATE A RESPONSE TO SEND BACK WITH THE TODO ITEM
        const response = {
            statusCode: 200,
            headers: {
                "Access-Control-Allow-Origin" : "*"
              },            
            body: JSON.stringify(result.Item),
        };

        callback(null, response);
    });
};
