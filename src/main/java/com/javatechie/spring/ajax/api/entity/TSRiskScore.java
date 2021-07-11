package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TS_RISK_SCORE")
public class TSRiskScore implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
		
	@Id
    @GeneratedValue
    private int id;
	@Embedded
	private TSRiskScoreData data;
	private String message;
	private String time;
	private String verdict;
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public TSRiskScoreData getData() {
		return data;
	}

	public void setData(TSRiskScoreData data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}
}