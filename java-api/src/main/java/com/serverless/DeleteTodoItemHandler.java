package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.DAL.TodoItem;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Map;

public class DeleteTodoItemHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        try {
            
            // GET THE ID FROM THE URL AND WRITE TO VARIABLE
            Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
            String todoItemId = pathParameters.get("id");

            // DELETE THE TODO ITEM BY PASSING THE ID
            Boolean success = new TodoItem().delete(todoItemId);

            // IF IT WAS A SUCCESS
            if (success) {

                // BUILD THE RESPONSE AND REUTNR A 204 TO SIGNIFY OK
                return ApiGatewayResponse.builder()
                        .setStatusCode(204)
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                        .build();
            } else {

                // OTHERWISE BUILD AND RETURN A 404 TO SAY THERE WAS NO ITEM FOUND
                return ApiGatewayResponse.builder()
                        .setStatusCode(404)
                        .setObjectBody("Todo with id: '" + todoItemId + "' not found.")
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                        .build();
            }
        } catch (Exception ex) {

            // CATCH EXCEPTIONS AND LOG AN ERROR
            logger.error("Error in deleting todo: " + ex);

            // BUILD THE ERROR RESPONSE - SET A 500 INTERNAL ERROR

            // TODO: BETTER HANDLING OF ERRORS HERE INSTEAD OF A BLANKET 500
            Response responseBody = new Response("Error in deleting todo: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();
        }
    }
}