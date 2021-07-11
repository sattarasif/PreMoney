package com.javatechie.spring.ajax.api.entity;

import javax.persistence.Embedded;

public class TSRiskScoreData {

	@Embedded
	private TSConsentInfo consentInfo;
	@Embedded
	private TSOther other;
	@Embedded
	private TSScoreInfo scoreInfo;
	private String telcoCode;

	public TSConsentInfo getConsentInfo() {
		return consentInfo;
	}

	public void setConsentInfo(TSConsentInfo consentInfo) {
		this.consentInfo = consentInfo;
	}

	public TSOther getOther() {
		return other;
	}

	public void setOther(TSOther other) {
		this.other = other;
	}

	public TSScoreInfo getScoreInfo() {
		return scoreInfo;
	}

	public void setScoreInfo(TSScoreInfo scoreInfo) {
		this.scoreInfo = scoreInfo;
	}

	public String getTelcoCode() {
		return telcoCode;
	}

	public void setTelcoCode(String telcoCode) {
		this.telcoCode = telcoCode;
	}

}