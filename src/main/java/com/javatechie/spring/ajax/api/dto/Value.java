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
@JsonPropertyOrder({ "first_name", "last_name", "middle_name" })
public class Value {

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("middle_name")
	private String middleName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("middle_name")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middle_name")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
