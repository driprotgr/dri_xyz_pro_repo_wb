package com.driverhire.exception;

public class DriverHireException extends Exception {
	
	private int errCode;
	private String errMsg;
	
	public DriverHireException(int code, String message) {
		super(message);
		errMsg = message;
		errCode = code;
	}
	public DriverHireException (Throwable cause){
		super(cause);
	}
	public DriverHireException (String message, Throwable cause){
		super(message, cause);
		errMsg = message;
	}

	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
