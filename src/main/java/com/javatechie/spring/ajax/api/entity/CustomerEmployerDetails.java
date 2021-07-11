package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_EMPLOYER_DTL")
public class CustomerEmployerDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private String employerType;
		private String employerName;
		private String employerContactNo;
		private String fullAddress;
		private String isPrimaryAddress;
		private String income;
		private String city;
		private String state;
		private String zipCode;
		private int userId;
		@Embedded
		private CommonDBFields commonDBFields;
		@Embedded
		private AdditionalFields additionalFields;
		
		
		public String getIncome() {
			return income;
		}
		public void setIncome(String income) {
			this.income = income;
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
		public String getEmployerType() {
			return employerType;
		}
		public void setEmployerType(String employerType) {
			this.employerType = employerType;
		}
		public String getEmployerName() {
			return employerName;
		}
		public void setEmployerName(String employerName) {
			this.employerName = employerName;
		}
		public String getEmployerContactNo() {
			return employerContactNo;
		}
		public void setEmployerContactNo(String employerContactNo) {
			this.employerContactNo = employerContactNo;
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
		
}
