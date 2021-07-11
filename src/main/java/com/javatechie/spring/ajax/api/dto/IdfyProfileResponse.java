package com.javatechie.spring.ajax.api.dto;

import org.springframework.http.HttpStatus;

public class IdfyProfileResponse {

	private String captureLink;
	private String profileId;
    private HttpStatus status;
    
	public String getCaptureLink() {
		return captureLink;
	}
	@Override
	public String toString() {
		return "IdfyProfileResponse [captureLink=" + captureLink + ", profileId=" + profileId + ", status=" + status
				+ "]";
	}
	public IdfyProfileResponse(String captureLink, String profileId, HttpStatus status) {
		super();
		this.captureLink = captureLink;
		this.profileId = profileId;
		this.status = status;
	}
	public IdfyProfileResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((captureLink == null) ? 0 : captureLink.hashCode());
		result = prime * result + ((profileId == null) ? 0 : profileId.hashCode());
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
		IdfyProfileResponse other = (IdfyProfileResponse) obj;
		if (captureLink == null) {
			if (other.captureLink != null)
				return false;
		} else if (!captureLink.equals(other.captureLink))
			return false;
		if (profileId == null) {
			if (other.profileId != null)
				return false;
		} else if (!profileId.equals(other.profileId))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	public void setCaptureLink(String captureLink) {
		this.captureLink = captureLink;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
    
    
}
