package com.driverhire.dto;

public class JsonResponse {
	private int responseCode;
	private Object responseBody;
	private String responseMessage;
	private String authToken;
	
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
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("{ \"responseCode\": \"");
		builder.append(getResponseCode());
		builder.append("\", \"responseBody\": \"");
		builder.append(getResponseBody());
		builder.append("\", \"authToken\": \"");
		builder.append(getAuthToken());
		builder.append("\"}");
		builder.append("\", \"responseMessage\": \"");
		builder.append(getResponseMessage());
		builder.append("\"}");
		/*{   "responseCode": 200,
			   "responseBody":    {
			      "userName": "giri",
			      "userEmail": "giri@abc.com",
			      "userCategory": "D",
			      "userId": 1,
			      "userMobile": "1234567890",
			      "password": null,
			      "accountType": "E",
			      "accountId": "1234567890",
			      "actFlag": "1",
			      "otp": null,
			      "authToken": "8zwxk7RML7npusDcd9YHRJguRCu6lGiRmmB3oQSngARUqWFkeLjF8q6Rpx54uLSX"
			   },
			   "othToken": "8zwxk7RML7npusDcd9YHRJguRCu6lGiRmmB3oQSngARUqWFkeLjF8q6Rpx54uLSX"
			}
	}*/
		return builder.toString();
	}
}
