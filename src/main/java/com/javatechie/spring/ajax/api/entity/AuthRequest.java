package com.javatechie.spring.ajax.api.entity;



public class AuthRequest {

    private String userName;//phone number
    private String password;//otp
    
    public AuthRequest() {
		super();
	}
	public AuthRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
