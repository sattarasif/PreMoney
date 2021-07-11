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
@JsonPropertyOrder({ "key", "resources", "result", "status", "task_id", "task_type" })
public class Task {

	@JsonProperty("key")
	private String key;
	@JsonProperty("resources")
	private List<String> resources = null;
	@JsonProperty("result")
	private Result result;
	@JsonProperty("status")
	private String status;
	@JsonProperty("task_id")
	private String taskId;
	@JsonProperty("task_type")
	private String taskType;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("key")
	public String getKey() {
		return key;
	}

	@JsonProperty("key")
	public void setKey(String key) {
		this.key = key;
	}

	@JsonProperty("resources")
	public List<String> getResources() {
		return resources;
	}

	@JsonProperty("resources")
	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	@JsonProperty("result")
	public Result getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(Result result) {
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

	@JsonProperty("task_type")
	public String getTaskType() {
		return taskType;
	}

	@JsonProperty("task_type")
	public void setTaskType(String taskType) {
		this.taskType = taskType;
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