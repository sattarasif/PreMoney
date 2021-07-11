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
@JsonPropertyOrder({ "first_name", "gender", "id_number", "last_name", "middle_name", "name_on_card", "source",
		"status" })
public class PanSourceOutput {

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("gender")
	private Object gender;
	@JsonProperty("id_number")
	private String idNumber;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("middle_name")
	private String middleName;
	@JsonProperty("name_on_card")
	private String nameOnCard;
	@JsonProperty("source")
	private String source;
	@JsonProperty("status")
	private String status;
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

	@JsonProperty("gender")
	public Object getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(Object gender) {
		this.gender = gender;
	}

	@JsonProperty("id_number")
	public String getIdNumber() {
		return idNumber;
	}

	@JsonProperty("id_number")
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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

	@JsonProperty("name_on_card")
	public String getNameOnCard() {
		return nameOnCard;
	}

	@JsonProperty("name_on_card")
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	@JsonProperty("source")
	public String getSource() {
		return source;
	}

	@JsonProperty("source")
	public void setSource(String source) {
		this.source = source;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
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