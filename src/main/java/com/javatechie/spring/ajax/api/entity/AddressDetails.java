package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_DTL")
public class AddressDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private String addressLine1;
		private String addressLine2;
		private String addressLine3;
		private String fullAddress;
		private String isPrimaryAddress;
		private String addressType;
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
		public String getAddressLine1() {
			return addressLine1;
		}
		public void setAddressLine1(String addressLine1) {
			this.addressLine1 = addressLine1;
		}
		public String getAddressLine2() {
			return addressLine2;
		}
		public void setAddressLine2(String addressLine2) {
			this.addressLine2 = addressLine2;
		}
		public String getAddressLine3() {
			return addressLine3;
		}
		public void setAddressLine3(String addressLine3) {
			this.addressLine3 = addressLine3;
		}
		public String getFullAddress() {
			return fullAddress;
		}
		public void setFullAddress(String fullAddress) {
			this.fullAddress = fullAddress;
		}
		public String getIsPrimaryAddress() {
			return isPrimaryAddress;
		}
		public void setIsPrimaryAddress(String isPrimaryAddress) {
			this.isPrimaryAddress = isPrimaryAddress;
		}
		public String getAddressType() {
			return addressType;
		}
		public void setAddressType(String addressType) {
			this.addressType = addressType;
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
