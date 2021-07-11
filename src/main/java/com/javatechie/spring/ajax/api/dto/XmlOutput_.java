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
@JsonPropertyOrder({ "address", "date_of_birth", "gender", "generated_at", "name", "photo", "reference_number",
		"year_of_birth" })
public class XmlOutput_ {

	@JsonProperty("address")
	private String address;
	@JsonProperty("date_of_birth")
	private String dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("generated_at")
	private String generatedAt;
	@JsonProperty("name")
	private String name;
	@JsonProperty("photo")
	private String photo;
	@JsonProperty("reference_number")
	private String referenceNumber;
	@JsonProperty("year_of_birth")
	private String yearOfBirth;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty("date_of_birth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("date_of_birth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("generated_at")
	public String getGeneratedAt() {
		return generatedAt;
	}

	@JsonProperty("generated_at")
	public void setGeneratedAt(String generatedAt) {
		this.generatedAt = generatedAt;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("photo")
	public String getPhoto() {
		return photo;
	}

	@JsonProperty("photo")
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@JsonProperty("reference_number")
	public String getReferenceNumber() {
		return referenceNumber;
	}

	@JsonProperty("reference_number")
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	@JsonProperty("year_of_birth")
	public String getYearOfBirth() {
		return yearOfBirth;
	}

	@JsonProperty("year_of_birth")
	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
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