package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_REF_DTL")
public class CustomerReferenceDetails implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int id;
		private String refName1;
		private String refContactNo1;
		private String relation1;
		private String refAddress1;
		
		private String refName2;
		private String refContactNo2;
		private String relation2;
		private String refAddress2;
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
		public String getRefName1() {
			return refName1;
		}
		public void setRefName1(String refName1) {
			this.refName1 = refName1;
		}
		public String getRefContactNo1() {
			return refContactNo1;
		}
		public void setRefContactNo1(String refContactNo1) {
			this.refContactNo1 = refContactNo1;
		}
		public String getRefName2() {
			return refName2;
		}
		public void setRefName2(String refName2) {
			this.refName2 = refName2;
		}
		
		public String getRefContactNo2() {
			return refContactNo2;
		}
		public void setRefContactNo2(String refContactNo2) {
			this.refContactNo2 = refContactNo2;
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
		public String getRelation1() {
			return relation1;
		}
		public void setRelation1(String relation1) {
			this.relation1 = relation1;
		}
		public String getRefAddress1() {
			return refAddress1;
		}
		public void setRefAddress1(String refAddress1) {
			this.refAddress1 = refAddress1;
		}
		public String getRelation2() {
			return relation2;
		}
		public void setRelation2(String relation2) {
			this.relation2 = relation2;
		}
		public String getRefAddress2() {
			return refAddress2;
		}
		public void setRefAddress2(String refAddress2) {
			this.refAddress2 = refAddress2;
		}

}
