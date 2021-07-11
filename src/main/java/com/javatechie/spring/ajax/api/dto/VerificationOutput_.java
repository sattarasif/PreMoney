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
@JsonPropertyOrder({ "digital_signature", "share_code", "xml_expired" })
public class VerificationOutput_ {

	@JsonProperty("digital_signature")
	private String digitalSignature;
	@JsonProperty("share_code")
	private String shareCode;
	@JsonProperty("xml_expired")
	private String xmlExpired;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("digital_signature")
	public String getDigitalSignature() {
		return digitalSignature;
	}

	@JsonProperty("digital_signature")
	public void setDigitalSignature(String digitalSignature) {
		this.digitalSignature = digitalSignature;
	}

	@JsonProperty("share_code")
	public String getShareCode() {
		return shareCode;
	}

	@JsonProperty("share_code")
	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	@JsonProperty("xml_expired")
	public String getXmlExpired() {
		return xmlExpired;
	}

	@JsonProperty("xml_expired")
	public void setXmlExpired(String xmlExpired) {
		this.xmlExpired = xmlExpired;
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