package com.javatechie.spring.ajax.api.controller;

public class OTPService {

	private String mobileNumber;
	private String otp;
	private long expiryTime;
	private String request_id;
	private String accessToken;
	
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public long getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(long expiryTime) {
		this.expiryTime = expiryTime;
	}
	public OTPService(String mobileNumber, String otp, long expiryTime) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
		this.expiryTime = expiryTime;
	}
	public OTPService( ) {
		
	}
}
