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
	
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("{ \"responseCode\": \"");
		builder.append(getResponseCode());
		builder.append("\", \"responseBody\": \"");
		builder.append(getResponseBody());
		builder.append("\", \"othToken\": \"");
		builder.append(getOthToken());
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
