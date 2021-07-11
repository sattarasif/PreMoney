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
"data",
"message",
"time",
"verdict"
})
public class TSOtpRequest {

@JsonProperty("data")
private TSOtpData data;
@JsonProperty("message")
private String message;
@JsonProperty("time")
private String time;
@JsonProperty("verdict")
private String verdict;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("data")
public TSOtpData getData() {
return data;
}

@JsonProperty("data")
public void setData(TSOtpData data) {
this.data = data;
}

@JsonProperty("message")
public String getMessage() {
return message;
}

@JsonProperty("message")
public void setMessage(String message) {
this.message = message;
}

@JsonProperty("time")
public String getTime() {
return time;
}

@JsonProperty("time")
public void setTime(String time) {
this.time = time;
}

@JsonProperty("verdict")
public String getVerdict() {
return verdict;
}

@JsonProperty("verdict")
public void setVerdict(String verdict) {
this.verdict = verdict;
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