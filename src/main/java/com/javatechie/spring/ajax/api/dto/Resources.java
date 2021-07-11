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
@JsonPropertyOrder({ "documents", "images", "text", "videos" })
public class Resources {

	@JsonProperty("documents")
	private List<Object> documents = null;
	@JsonProperty("images")
	private List<Image> images = null;
	@JsonProperty("text")
	private List<Text> text = null;
	@JsonProperty("videos")
	private List<Object> videos = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("documents")
	public List<Object> getDocuments() {
		return documents;
	}

	@JsonProperty("documents")
	public void setDocuments(List<Object> documents) {
		this.documents = documents;
	}

	@JsonProperty("images")
	public List<Image> getImages() {
		return images;
	}

	@JsonProperty("images")
	public void setImages(List<Image> images) {
		this.images = images;
	}

	@JsonProperty("text")
	public List<Text> getText() {
		return text;
	}

	@JsonProperty("text")
	public void setText(List<Text> text) {
		this.text = text;
	}

	@JsonProperty("videos")
	public List<Object> getVideos() {
		return videos;
	}

	@JsonProperty("videos")
	public void setVideos(List<Object> videos) {
		this.videos = videos;
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