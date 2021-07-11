package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KYC_DTL")
public class KYCDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private String customerImg;
		private String adhaarFrontImg;
		private String adhaarBackImg;
		private String panCardImg;
		private String passportFrontImg;
		private String passportBackImg;
		private String dlImg;
		private String cancelCheckImg;
		private String passBookImg;
		private String electricityBillImg;
		private int userId;
		@Embedded
		private CommonDBFields commonDBFields;
		@Embedded
		private AdditionalFields additionalFields;
		
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
		public String getCustomerImg() {
			return customerImg;
		}
		public void setCustomerImg(String customerImg) {
			this.customerImg = customerImg;
		}
		public String getAdhaarFrontImg() {
			return adhaarFrontImg;
		}
		public void setAdhaarFrontImg(String adhaarFrontImg) {
			this.adhaarFrontImg = adhaarFrontImg;
		}
		public String getAdhaarBackImg() {
			return adhaarBackImg;
		}
		public void setAdhaarBackImg(String adhaarBackImg) {
			this.adhaarBackImg = adhaarBackImg;
		}
		public String getPanCardImg() {
			return panCardImg;
		}
		public void setPanCardImg(String panCardImg) {
			this.panCardImg = panCardImg;
		}
		public String getPassportFrontImg() {
			return passportFrontImg;
		}
		public void setPassportFrontImg(String passportFrontImg) {
			this.passportFrontImg = passportFrontImg;
		}
		public String getPassportBackImg() {
			return passportBackImg;
		}
		public void setPassportBackImg(String passportBackImg) {
			this.passportBackImg = passportBackImg;
		}
		public String getDlImg() {
			return dlImg;
		}
		public void setDlImg(String dlImg) {
			this.dlImg = dlImg;
		}
		
		public String getCancelCheckImg() {
			return cancelCheckImg;
		}
		public void setCancelCheckImg(String cancelCheckImg) {
			this.cancelCheckImg = cancelCheckImg;
		}
		public String getPassBookImg() {
			return passBookImg;
		}
		public void setPassBookImg(String passBookImg) {
			this.passBookImg = passBookImg;
		}
		public String getElectricityBillImg() {
			return electricityBillImg;
		}
		public void setElectricityBillImg(String electricityBillImg) {
			this.electricityBillImg = electricityBillImg;
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
