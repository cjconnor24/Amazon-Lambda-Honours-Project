package com.serverless;

import java.util.Map;

/**
This class will represent the response fed back to the calling agent
 */
public class Response {

	private final String message;
	private final Map<String, Object> input;

	// TODO: POSSIBLY OVERLOAD THIS SO IT DOESNT DUMP THE WHOLE INPUT OBJECT BACK
	public Response(String message, Map<String, Object> input) {
		this.message = message;
		this.input = input;
	}

	/**
	ACCESSOR FOR MESSAGE
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	ACCESSOR FOR THE INPUT MAP - THIS WILL ACTUALLY RETURN THE ENTIRE REQUEST AS RESPONSE
	 */
	public Map<String, Object> getInput() {
		return this.input;
	}
}
