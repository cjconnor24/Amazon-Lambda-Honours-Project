'use strict';

const AWS = require('aws-sdk'); // eslint-disable-line import/no-extraneous-dependencies
const dynamoDb = new AWS.DynamoDB.DocumentClient();

module.exports.delete = (event, context, callback) => {

    //BIULD THE PARAMS FOR DYNAMO
  const params = {
    TableName: process.env.TODO_TABLE_NAME,
    Key: {
      id: event.pathParameters.id,
    },
  };

  // DELETE THE RECORD IN DYNAMO
  dynamoDb.delete(params, (error) => {
    
    // CATCH ANY ERRORS
    if (error) {
      console.error(error);
      callback(null, {
        statusCode: error.statusCode || 501,
        headers: { 'Content-Type': 'text/plain' },
        body: 'Couldn\'t remove the todo item.',
      });
      return;
    }

    // create a response
    const response = {
      statusCode: 200,
      headers: {
        "Access-Control-Allow-Origin" : "*"
      },      
      body: JSON.stringify({}),
    };
    callback(null, response);
  });
};
