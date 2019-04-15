package com.serverless;

import java.util.Date;
import java.util.Map;

public class Response {

	private final String message;
	private final long timestamp;
	private final Map<String, Object> input;

	public Response(String message, Map<String, Object> input) {
		this.message = message;
		this.timestamp = new Date().getTime();
		this.input = input;
	}

	public String getMessage() {
		return this.message;
	}

	public long getTimestamp() {return this.timestamp;}

	public Map<String, Object> getInput() {
		return this.input;
	}
}
