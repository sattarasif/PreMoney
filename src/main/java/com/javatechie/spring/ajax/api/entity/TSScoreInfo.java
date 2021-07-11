package com.javatechie.spring.ajax.api.entity;

public class TSScoreInfo {

	private Integer requestId;
	private String score;
	private String scoreVersion;

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScoreVersion() {
		return scoreVersion;
	}

	public void setScoreVersion(String scoreVersion) {
		this.scoreVersion = scoreVersion;
	}

}
