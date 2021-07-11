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
@JsonPropertyOrder({
"consent_info",
"other",
"score_info",
"telco_code"
})
public class TSRiskScoreData {

@JsonProperty("consent_info")
private TSConsentInfo consentInfo;
@JsonProperty("other")
private TSOther other;
@JsonProperty("score_info")
private TSScoreInfo scoreInfo;
@JsonProperty("telco_code")
private String telcoCode;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("consent_info")
public TSConsentInfo getConsentInfo() {
return consentInfo;
}

@JsonProperty("consent_info")
public void setConsentInfo(TSConsentInfo consentInfo) {
this.consentInfo = consentInfo;
}

@JsonProperty("other")
public TSOther getOther() {
return other;
}

@JsonProperty("other")
public void setOther(TSOther other) {
this.other = other;
}

@JsonProperty("score_info")
public TSScoreInfo getScoreInfo() {
return scoreInfo;
}

@JsonProperty("score_info")
public void setScoreInfo(TSScoreInfo scoreInfo) {
this.scoreInfo = scoreInfo;
}

@JsonProperty("telco_code")
public String getTelcoCode() {
return telcoCode;
}

@JsonProperty("telco_code")
public void setTelcoCode(String telcoCode) {
this.telcoCode = telcoCode;
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