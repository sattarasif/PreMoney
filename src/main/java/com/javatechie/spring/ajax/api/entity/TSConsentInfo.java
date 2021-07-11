package com.javatechie.spring.ajax.api.entity;

import javax.persistence.Column;

public class TSConsentInfo {

	private String expiredAt;
	@Column(name="CONSENT_ID")
	private Integer id;
	private Integer maxUsages;
	private Integer usedCount;

	public String getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(String expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaxUsages() {
		return maxUsages;
	}

	public void setMaxUsages(Integer maxUsages) {
		this.maxUsages = maxUsages;
	}

	public Integer getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
}
