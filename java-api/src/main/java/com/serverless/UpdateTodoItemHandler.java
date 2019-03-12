package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.DAL.TodoItem;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class UpdateTodoItemHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        try {
            // get the 'pathParameters' from input
            Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
            String todoId = pathParameters.get("id");

            // get the ToDoItem by id
            TodoItem todoItem = new TodoItem().get(todoId);

            // send the response back
            if (todoItem != null) {

                // UPDATE THE FIELDS
                JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
                todoItem.setText(body.get("text").asText());
                todoItem.setChecked((boolean) body.get("checked").asBoolean());
                todoItem.setUpdatedAt(new Date().getTime());
                todoItem.update(todoItem);

                return ApiGatewayResponse.builder()
                        .setStatusCode(200)
                        .setObjectBody(todoItem)
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                        .build();
            } else {
                return ApiGatewayResponse.builder()
                        .setStatusCode(404)
                        .setObjectBody("Todo Item with id: '" + todoId + "' not found.")
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                        .build();
            }
        } catch (Exception ex) {
            logger.error("Error in retrieving todo item: " + ex);

            // send the error response back
            Response responseBody = new Response("Error in retrieving todo item: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
                    .build();
        }
    }
}
