package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_ACCOUNTS_DTL")
public class CustomerAccountsDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private int appliedAmt;
		private int durationInDays;
		private int userId;
		private String cibilScore;
		private String equifax;
		private String remarks;
		private String letterConsentOtp;
		private Date letterConsentDate;
		@Embedded
		private CommonDBFields commonDBFields;
		@Embedded
		private AdditionalFields additionalFields;
		
		
		public String getLetterConsentOtp() {
			return letterConsentOtp;
		}
		public void setLetterConsentOtp(String letterConsentOtp) {
			this.letterConsentOtp = letterConsentOtp;
		}
		public Date getLetterConsentDate() {
			return letterConsentDate;
		}
		public void setLetterConsentDate(Date letterConsentDate) {
			this.letterConsentDate = letterConsentDate;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getCibilScore() {
			return cibilScore;
		}
		public void setCibilScore(String cibilScore) {
			this.cibilScore = cibilScore;
		}
		public String getEquifax() {
			return equifax;
		}
		public void setEquifax(String equifax) {
			this.equifax = equifax;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getAppliedAmt() {
			return appliedAmt;
		}
		public void setAppliedAmt(int appliedAmt) {
			this.appliedAmt = appliedAmt;
		}
		public int getDurationInDays() {
			return durationInDays;
		}
		public void setDurationInDays(int durationInDays) {
			this.durationInDays = durationInDays;
		}
		
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
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
