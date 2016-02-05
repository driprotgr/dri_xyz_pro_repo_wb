package com.driverhire.dto;

public class JsonResponse {
	private int responseCode;
	private Object responseBody;
	private String othToken;
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public Object getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	public String getOthToken() {
		return othToken;
	}
	public void setOthToken(String othToken) {
		this.othToken = othToken;
	}
}
