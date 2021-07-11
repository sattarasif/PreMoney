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
"expired_at",
"id",
"max_usages",
"used_count"
})
public class TSConsentInfo {

@JsonProperty("expired_at")
private String expiredAt;
@JsonProperty("id")
private Integer id;
@JsonProperty("max_usages")
private Integer maxUsages;
@JsonProperty("used_count")
private Integer usedCount;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("expired_at")
public String getExpiredAt() {
return expiredAt;
}

@JsonProperty("expired_at")
public void setExpiredAt(String expiredAt) {
this.expiredAt = expiredAt;
}

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("max_usages")
public Integer getMaxUsages() {
return maxUsages;
}

@JsonProperty("max_usages")
public void setMaxUsages(Integer maxUsages) {
this.maxUsages = maxUsages;
}

@JsonProperty("used_count")
public Integer getUsedCount() {
return usedCount;
}

@JsonProperty("used_count")
public void setUsedCount(Integer usedCount) {
this.usedCount = usedCount;
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
