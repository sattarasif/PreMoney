package com.javatechie.spring.ajax.api.dto;

import org.springframework.http.HttpStatus;

public class AuthenticationResponse {
	
    private String authenticationToken;
    private HttpStatus status;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authenticationToken == null) ? 0 : authenticationToken.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationResponse other = (AuthenticationResponse) obj;
		if (authenticationToken == null) {
			if (other.authenticationToken != null)
				return false;
		} else if (!authenticationToken.equals(other.authenticationToken))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthenticationResponse(String authenticationToken, HttpStatus status) {
		super();
		this.authenticationToken = authenticationToken;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "AuthenticationResponse [authenticationToken=" + authenticationToken + ", status=" + status + "]";
	}
	public String getAuthenticationToken() {
		return authenticationToken;
	}
	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
    
    
}
