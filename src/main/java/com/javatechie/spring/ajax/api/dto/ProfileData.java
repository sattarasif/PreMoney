package com.javatechie.spring.ajax.api.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "completed_at", "created_at", "email", "mobile_number", "notes", "performed_by", "purged_at" })
public class ProfileData {

	@JsonProperty("completed_at")
	private String completedAt;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("email")
	private List<Object> email = null;
	@JsonProperty("mobile_number")
	private List<Object> mobileNumber = null;
	@JsonProperty("notes")
	private Object notes;
	@JsonProperty("performed_by")
	private List<Object> performedBy = null;
	@JsonProperty("purged_at")
	private Object purgedAt;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("completed_at")
	public String getCompletedAt() {
		return completedAt;
	}

	@JsonProperty("completed_at")
	public void setCompletedAt(String completedAt) {
		this.completedAt = completedAt;
	}

	@JsonProperty("created_at")
	public String getCreatedAt() {
		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty("email")
	public List<Object> getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(List<Object> email) {
		this.email = email;
	}

	@JsonProperty("mobile_number")
	public List<Object> getMobileNumber() {
		return mobileNumber;
	}

	@JsonProperty("mobile_number")
	public void setMobileNumber(List<Object> mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@JsonProperty("notes")
	public Object getNotes() {
		return notes;
	}

	@JsonProperty("notes")
	public void setNotes(Object notes) {
		this.notes = notes;
	}

	@JsonProperty("performed_by")
	public List<Object> getPerformedBy() {
		return performedBy;
	}

	@JsonProperty("performed_by")
	public void setPerformedBy(List<Object> performedBy) {
		this.performedBy = performedBy;
	}

	@JsonProperty("purged_at")
	public Object getPurgedAt() {
		return purgedAt;
	}

	@JsonProperty("purged_at")
	public void setPurgedAt(Object purgedAt) {
		this.purgedAt = purgedAt;
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