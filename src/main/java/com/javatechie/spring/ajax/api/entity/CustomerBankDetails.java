package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_BANK_DTL")
public class CustomerBankDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private String bankAccountNo;
		private String bankName;
		private String IFSCCode;
		private String fullAddress;
		private String city;
		private String state;
		private String zipCode;
		private int userId;
		@Embedded
		private CommonDBFields commonDBFields;
		@Embedded
		private AdditionalFields additionalFields;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getBankAccountNo() {
			return bankAccountNo;
		}
		public void setBankAccountNo(String bankAccountNo) {
			this.bankAccountNo = bankAccountNo;
		}
		public String getBankName() {
			return bankName;
		}
		public void setBankName(String bankName) {
			this.bankName = bankName;
		}
		public String getIFSCCode() {
			return IFSCCode;
		}
		public void setIFSCCode(String iFSCCode) {
			IFSCCode = iFSCCode;
		}
		
		public String getFullAddress() {
			return fullAddress;
		}
		public void setFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getZipCode() {
			return zipCode;
		}
		public void setZipCode(String zipCode) {
			this.zipCode = zipCode;
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
