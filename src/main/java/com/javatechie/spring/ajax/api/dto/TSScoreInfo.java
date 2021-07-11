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
"request_id",
"score",
"score_version"
})
public class TSScoreInfo {

@JsonProperty("request_id")
private Integer requestId;
@JsonProperty("score")
private String score;
@JsonProperty("score_version")
private String scoreVersion;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("request_id")
public Integer getRequestId() {
return requestId;
}

@JsonProperty("request_id")
public void setRequestId(Integer requestId) {
this.requestId = requestId;
}

@JsonProperty("score")
public String getScore() {
return score;
}

@JsonProperty("score")
public void setScore(String score) {
this.score = score;
}

@JsonProperty("score_version")
public String getScoreVersion() {
return scoreVersion;
}

@JsonProperty("score_version")
public void setScoreVersion(String scoreVersion) {
this.scoreVersion = scoreVersion;
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
