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
@JsonPropertyOrder({
"change_log",
"changed",
"verification_output",
"xml_output"
})
public class ManualResponse {

@JsonProperty("change_log")
private List<Object> changeLog = null;
@JsonProperty("changed")
private Boolean changed;
@JsonProperty("verification_output")
private VerificationOutput_ verificationOutput;
@JsonProperty("xml_output")
private XmlOutput_ xmlOutput;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("change_log")
public List<Object> getChangeLog() {
return changeLog;
}

@JsonProperty("change_log")
public void setChangeLog(List<Object> changeLog) {
this.changeLog = changeLog;
}

@JsonProperty("changed")
public Boolean getChanged() {
return changed;
}

@JsonProperty("changed")
public void setChanged(Boolean changed) {
this.changed = changed;
}

@JsonProperty("verification_output")
public VerificationOutput_ getVerificationOutput() {
return verificationOutput;
}

@JsonProperty("verification_output")
public void setVerificationOutput(VerificationOutput_ verificationOutput) {
this.verificationOutput = verificationOutput;
}

@JsonProperty("xml_output")
public XmlOutput_ getXmlOutput() {
return xmlOutput;
}

@JsonProperty("xml_output")
public void setXmlOutput(XmlOutput_ xmlOutput) {
this.xmlOutput = xmlOutput;
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