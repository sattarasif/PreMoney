package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "CUSTOMER_DTL")
public class CustomerDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private String firstName;
		private String MiddleName;
		private String lastName;
		private String fullName;
		private Date dob;
		private int userId;
		private String emailAddress;
		private String gender;
		private String maritalStatus;
		private String phoneNo;
		private String adhaarNo;
		private String panNo;
		private String language;
		private String fatherName;
		private Date lastDelequencyClearedOn;
		private Date lastDeleguencyOn;
		private String maxDPDEverReached;
		@Embedded
		private CommonDBFields commonDBFields;
		@Embedded
		private AdditionalFields additionalFields;
		
		
		public String getMaritalStatus() {
			return maritalStatus;
		}
		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}
		public String getAdhaarNo() {
			return adhaarNo;
		}
		public void setAdhaarNo(String adhaarNo) {
			this.adhaarNo = adhaarNo;
		}
		public String getPanNo() {
			return panNo;
		}
		public void setPanNo(String panNo) {
			this.panNo = panNo;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return MiddleName;
		}
		public void setMiddleName(String middleName) {
			MiddleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getFatherName() {
			return fatherName;
		}
		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}
		public Date getLastDelequencyClearedOn() {
			return lastDelequencyClearedOn;
		}
		public void setLastDelequencyClearedOn(Date lastDelequencyClearedOn) {
			this.lastDelequencyClearedOn = lastDelequencyClearedOn;
		}
		public Date getLastDeleguencyOn() {
			return lastDeleguencyOn;
		}
		public void setLastDeleguencyOn(Date lastDeleguencyOn) {
			this.lastDeleguencyOn = lastDeleguencyOn;
		}
		public String getMaxDPDEverReached() {
			return maxDPDEverReached;
		}
		public void setMaxDPDEverReached(String maxDPDEverReached) {
			this.maxDPDEverReached = maxDPDEverReached;
		}
		
		public CommonDBFields getCommonDBFields() {
			return commonDBFields;
		}
		public void setCommonDBFields(CommonDBFields commonDBFields) {
			this.commonDBFields = commonDBFields;
		}
		public AdditionalFields getAdditionalFields() {
			return additionalFields;
		}
		public void setAdditionalFields(AdditionalFields additionalFields) {
			this.additionalFields = additionalFields;
		}
		
		
		

}
