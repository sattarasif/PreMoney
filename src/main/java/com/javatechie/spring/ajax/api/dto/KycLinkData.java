package com.javatechie.spring.ajax.api.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "capture_expires_at", "capture_link", "profile_id" })
public class KycLinkData {

	@JsonProperty("capture_expires_at")
	private Object captureExpiresAt;
	@JsonProperty("capture_link")
	private String captureLink;
	@JsonProperty("profile_id")
	private String profileId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("capture_expires_at")
	public Object getCaptureExpiresAt() {
		return captureExpiresAt;
	}

	@JsonProperty("capture_expires_at")
	public void setCaptureExpiresAt(Object captureExpiresAt) {
		this.captureExpiresAt = captureExpiresAt;
	}

	@JsonProperty("capture_link")
	public String getCaptureLink() {
		return captureLink;
	}

	@JsonProperty("capture_link")
	public void setCaptureLink(String captureLink) {
		this.captureLink = captureLink;
	}

	@JsonProperty("profile_id")
	public String getProfileId() {
		return profileId;
	}

	@JsonProperty("profile_id")
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}