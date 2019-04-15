package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.DAL.TodoItem;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class GetTodoItemHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        try {
            
            // GET THE ID FROM THE URL AND ASSIGN TO VARIABLE
            Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
            String todoId = pathParameters.get("id");

            // RETRIEVE THE ITEM FROM THE DAO METHODS BY PASSING THE ID
            TodoItem todoItem = new TodoItem().get(todoId);

            // IF IT WAS SUCCESFUL - RETURN THE TODO ITEM
            if (todoItem != null) {
                return ApiGatewayResponse.builder()
                        .setStatusCode(200)
                        .setObjectBody(todoItem)
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                        .build();
            } else {
                // OTHERWISE BUILD A 404
                return ApiGatewayResponse.builder()
                        .setStatusCode(404)
                        .setObjectBody("Todo Item with id: '" + todoId + "' not found.")
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                        .build();
            }
        } catch (Exception ex) {
            
            // CATCH AND LOG THE ERROR
            logger.error("Error in retrieving todo item: " + ex);

            // BUILD THE ERROR RESPONSE AND RETURN A 500
            // TODO: NEEDS BETTER ERROR HANDLING
            Response responseBody = new Response("Error in retrieving todo item: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();
        }
    }
}
