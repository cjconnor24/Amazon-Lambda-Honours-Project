'use strict';

module.exports.memTest = async (event, context) => {
  return {
    statusCode: 200,
    body: JSON.stringify({
      timestamp: new Date().getTime(),
      message: 'NodeJS Lambda Response. Memory Test. Function executed successfully!'
      //input: event,
    }),
  };
};
