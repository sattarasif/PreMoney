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
"verification_output",
"xml_output"
})
public class AutomatedResponse {

@JsonProperty("verification_output")
private VerificationOutput verificationOutput;
@JsonProperty("xml_output")
private XmlOutput xmlOutput;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("verification_output")
public VerificationOutput getVerificationOutput() {
return verificationOutput;
}

@JsonProperty("verification_output")
public void setVerificationOutput(VerificationOutput verificationOutput) {
this.verificationOutput = verificationOutput;
}

@JsonProperty("xml_output")
public XmlOutput getXmlOutput() {
return xmlOutput;
}

@JsonProperty("xml_output")
public void setXmlOutput(XmlOutput xmlOutput) {
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
