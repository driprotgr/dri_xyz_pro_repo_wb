package com.driverhire.constant;

public enum AccountType {
	FACEBOOK('F'), GOOGLE('G'), MOBILE('M'); 
	private char value;
	 
	private AccountType(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
}
