package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.javatechie.spring.ajax.api.dto.KycResult;

@Entity
@Table(name = "CAPTURE_KYC")
public class CaptureKyc implements Serializable{
	
	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	@GeneratedValue
	private int id;
	private String config_id;
	private String completed_at;
	private String created_at;
	private String profile_id;
	private String reference_id;
	
	@Embedded
	private KycResult result;
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConfig_id() {
		return config_id;
	}
	public void setConfig_id(String config_id) {
		this.config_id = config_id;
	}
	public String getCompleted_at() {
		return completed_at;
	}
	public void setCompleted_at(String completed_at) {
		this.completed_at = completed_at;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public KycResult getResult() {
		return result;
	}
	public void setResult(KycResult result) {
		this.result = result;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
		
}
