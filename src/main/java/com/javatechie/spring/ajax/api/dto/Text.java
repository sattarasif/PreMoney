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
@JsonPropertyOrder({ "attr", "metadata", "ref_id", "source", "tags", "type", "value" })
public class Text {

	@JsonProperty("attr")
	private String attr;
	@JsonProperty("metadata")
	private Metadata_ metadata;
	@JsonProperty("ref_id")
	private String refId;
	@JsonProperty("source")
	private Integer source;
	@JsonProperty("tags")
	private Object tags;
	@JsonProperty("type")
	private Object type;
	@JsonProperty("value")
	private Value value;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("attr")
	public String getAttr() {
		return attr;
	}

	@JsonProperty("attr")
	public void setAttr(String attr) {
		this.attr = attr;
	}

	@JsonProperty("metadata")
	public Metadata_ getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata_ metadata) {
		this.metadata = metadata;
	}

	@JsonProperty("ref_id")
	public String getRefId() {
		return refId;
	}

	@JsonProperty("ref_id")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("source")
	public Integer getSource() {
		return source;
	}

	@JsonProperty("source")
	public void setSource(Integer source) {
		this.source = source;
	}

	@JsonProperty("tags")
	public Object getTags() {
		return tags;
	}

	@JsonProperty("tags")
	public void setTags(Object tags) {
		this.tags = tags;
	}

	@JsonProperty("type")
	public Object getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(Object type) {
		this.type = type;
	}

	@JsonProperty("value")
	public Value getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(Value value) {
		this.value = value;
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