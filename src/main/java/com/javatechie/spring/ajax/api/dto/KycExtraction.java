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
@JsonPropertyOrder({ "config", "profile_data", "profile_id", "reference_id", "resources", "reviewer_action", "status",
		"status_detail", "tasks", "version" })
public class KycExtraction {

	@JsonProperty("config")
	private Config config;
	@JsonProperty("profile_data")
	private ProfileData profileData;
	@JsonProperty("profile_id")
	private String profileId;
	@JsonProperty("reference_id")
	private String referenceId;
	@JsonProperty("resources")
	private Resources resources;
	@JsonProperty("reviewer_action")
	private Object reviewerAction;
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_detail")
	private Object statusDetail;
	@JsonProperty("tasks")
	private List<Task> tasks = null;
	@JsonProperty("version")
	private String version;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("config")
	public Config getConfig() {
		return config;
	}

	@JsonProperty("config")
	public void setConfig(Config config) {
		this.config = config;
	}

	@JsonProperty("profile_data")
	public ProfileData getProfileData() {
		return profileData;
	}

	@JsonProperty("profile_data")
	public void setProfileData(ProfileData profileData) {
		this.profileData = profileData;
	}

	@JsonProperty("profile_id")
	public String getProfileId() {
		return profileId;
	}

	@JsonProperty("profile_id")
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@JsonProperty("reference_id")
	public String getReferenceId() {
		return referenceId;
	}

	@JsonProperty("reference_id")
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	@JsonProperty("resources")
	public Resources getResources() {
		return resources;
	}

	@JsonProperty("resources")
	public void setResources(Resources resources) {
		this.resources = resources;
	}

	@JsonProperty("reviewer_action")
	public Object getReviewerAction() {
		return reviewerAction;
	}

	@JsonProperty("reviewer_action")
	public void setReviewerAction(Object reviewerAction) {
		this.reviewerAction = reviewerAction;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("status_detail")
	public Object getStatusDetail() {
		return statusDetail;
	}

	@JsonProperty("status_detail")
	public void setStatusDetail(Object statusDetail) {
		this.statusDetail = statusDetail;
	}

	@JsonProperty("tasks")
	public List<Task> getTasks() {
		return tasks;
	}

	@JsonProperty("tasks")
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
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