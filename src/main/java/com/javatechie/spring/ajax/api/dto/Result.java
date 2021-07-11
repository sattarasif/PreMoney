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
@JsonPropertyOrder({ "automated_response", "manual_response" })
public class Result {

	@JsonProperty("automated_response")
	private AutomatedResponse automatedResponse;
	@JsonProperty("manual_response")
	private ManualResponse manualResponse;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("automated_response")
	public AutomatedResponse getAutomatedResponse() {
		return automatedResponse;
	}

	@JsonProperty("automated_response")
	public void setAutomatedResponse(AutomatedResponse automatedResponse) {
		this.automatedResponse = automatedResponse;
	}

	@JsonProperty("manual_response")
	public ManualResponse getManualResponse() {
		return manualResponse;
	}

	@JsonProperty("manual_response")
	public void setManualResponse(ManualResponse manualResponse) {
		this.manualResponse = manualResponse;
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