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
            
            // GET THE ID FROM THE URL
            Map<String, String> pathParameters = (Map<String, String>) input.get("pathParameters");
            String todoId = pathParameters.get("id");

            // GET THE ITEM IF IT EXISTS IN THE DB
            TodoItem todoItem = new TodoItem().get(todoId);

            // IF THE ITEM IS FOUND
            if (todoItem != null) {

                // GET THE JSON FROM THE BODY OF THE REQUEST AND UPDATE THE OBJECT PROPERTIES
                JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
                todoItem.setText(body.get("text").asText());
                todoItem.setChecked((boolean) body.get("checked").asBoolean());
                todoItem.setUpdatedAt(new Date().getTime());

                // SAVE THE UPDATED OBJECT IN THE DB USING THE DAO METHODS
                todoItem.update(todoItem);

                // BIULD THE RESPONSE AND RETURN 200 TO CALLING AGENTS
                return ApiGatewayResponse.builder()
                        .setStatusCode(200)
                        .setObjectBody(todoItem)
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                        .build();
            } else {

                // OTHERWISE BUILD AND RETURN 404 AS TODO WASNT FOUND
                return ApiGatewayResponse.builder()
                        .setStatusCode(404)
                        .setObjectBody("Todo Item with id: '" + todoId + "' not found.")
                        .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                        .build();
            }
        } catch (Exception ex) {

            // CATCH EXCEPTION AND LOG TO LOGS
            logger.error("Error in retrieving todo item: " + ex);

            // BUILD THE ERROR RESPONSE AND RETURN TO CALLING AGENT
            // TODO: NEEDS BETTER ERROR HANDLING HERE
            Response responseBody = new Response("Error in retrieving todo item: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();
        }
    }
}
