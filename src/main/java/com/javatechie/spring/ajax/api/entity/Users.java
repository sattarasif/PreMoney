package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class Users implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
	    private String name;
	    private String phoneNumber;
	    private int verified;
	    private String email;
	    private String otp;
	    private String auth_token;
	    private String role;
	    private String disable;
	    private String status;
	    private String approvalStatus;
	    private Date created_date;
	    private Date updated_date;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public int getVerified() {
			return verified;
		}
		public void setVerified(int verified) {
			this.verified = verified;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getOtp() {
			return otp;
		}
		public void setOtp(String otp) {
			this.otp = otp;
		}
		public String getAuth_token() {
			return auth_token;
		}
		public void setAuth_token(String auth_token) {
			this.auth_token = auth_token;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getDisable() {
			return disable;
		}
		public void setDisable(String disable) {
			this.disable = disable;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		public String getApprovalStatus() {
			return approvalStatus;
		}
		public void setApprovalStatus(String approvalStatus) {
			this.approvalStatus = approvalStatus;
		}
		public Date getCreated_date() {
			return created_date;
		}
		public void setCreated_date(Date created_date) {
			this.created_date = created_date;
		}
		public Date getUpdated_date() {
			return updated_date;
		}
		public void setUpdated_date(Date updated_date) {
			this.updated_date = updated_date;
		}
	    
	    
	    
	    
}
