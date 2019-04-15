package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.DAL.TodoItem;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ListTodoItemHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
        try {
            
            // GET ALL TODO LIST ITEMS FROM THE DAO METHOD
            List<TodoItem> todoList = new TodoItem().list();

            // BUILD THE RESPONSE AND RETURN
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(todoList)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();
        } catch (Exception ex) {

            // CATCH THE ERRORS AND OUTPUT TO CONSOLE
            logger.error("Error in listing todo list: " + ex);

            // BUILD RESPONSE ERROR AND RETURN 500
            // TODO: NEEDS BETTER ERROR HANDLING HERE
            Response responseBody = new Response("Error in listing todo list: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();
        }
    }
}
