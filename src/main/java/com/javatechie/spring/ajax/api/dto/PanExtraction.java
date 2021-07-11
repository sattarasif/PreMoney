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
@JsonPropertyOrder({ "action", "completed_at", "created_at", "group_id", "request_id", "result", "status", "task_id",
		"type" })
public class PanExtraction {

	@JsonProperty("action")
	private String action;
	@JsonProperty("completed_at")
	private String completedAt;
	@JsonProperty("created_at")
	private String createdAt;
	@JsonProperty("group_id")
	private String groupId;
	@JsonProperty("request_id")
	private String requestId;
	@JsonProperty("result")
	private PanResult result;
	@JsonProperty("status")
	private String status;
	@JsonProperty("task_id")
	private String taskId;
	@JsonProperty("type")
	private String type;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("action")
	public String getAction() {
		return action;
	}

	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}

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

	@JsonProperty("group_id")
	public String getGroupId() {
		return groupId;
	}

	@JsonProperty("group_id")
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@JsonProperty("request_id")
	public String getRequestId() {
		return requestId;
	}

	@JsonProperty("request_id")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@JsonProperty("result")
	public PanResult getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(PanResult result) {
		this.result = result;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("task_id")
	public String getTaskId() {
		return taskId;
	}

	@JsonProperty("task_id")
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
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