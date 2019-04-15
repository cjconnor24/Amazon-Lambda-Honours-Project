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

public class CreateTodoItemHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {


        try {
            
            // GET THE RESPONSE BODY FROM THE REQUEST
            JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

            // CREATE NEW TODO POJO ITEM
            TodoItem todoItem = new TodoItem();

            // ASSIGN ALL THE VALUES FROM THE BODY AND GET THE NEW TIME STAMPS
            todoItem.setText(body.get("text").asText());
            todoItem.setChecked((boolean) body.get("checked").asBoolean());
            todoItem.setCreatedAt(new Date().getTime());
            todoItem.setUpdatedAt(new Date().getTime());

            // SAVE THE ITEM VIA THE REQUEST MAPPER / DAO METHODS INSIDE THE POJO
            todoItem.save(todoItem);

            // BUILD THE RESPONSE AND SEND IT BACK TO THE CALLING AGENTS
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(todoItem)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();

        } catch (Exception ex) {

            // CATCH ANY ERRORS AND LOG
            logger.error("Error in saving To-do Item: " + ex);

            // BUILD AN ERROR RESPONSE
            Response responseBody = new Response("Error in saving To-do Item: ", input);
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody(responseBody)
                    .setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless Framework"))
                    .build();
        }
    }
}
