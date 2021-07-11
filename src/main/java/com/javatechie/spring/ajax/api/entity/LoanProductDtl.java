package com.javatechie.spring.ajax.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOAN_PRODUCT_DETAILS")
public class LoanProductDtl implements Serializable{

	private static final long serialVersionUID = 2268664456193143844L;
	@Id
	    @GeneratedValue
	    private int product_id;
		private double interest_pa;
		private double interest_product;
		private double processing_fees;
		private double penalty;
		private int loan_days;
		public int getProduct_id() {
			return product_id;
		}
		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}
		public double getInterest_pa() {
			return interest_pa;
		}
		public void setInterest_pa(double interest_pa) {
			this.interest_pa = interest_pa;
		}
		public double getInterest_product() {
			return interest_product;
		}
		public void setInterest_product(double interest_product) {
			this.interest_product = interest_product;
		}
		public double getProcessing_fees() {
			return processing_fees;
		}
		public void setProcessing_fees(double processing_fees) {
			this.processing_fees = processing_fees;
		}
		public double getPenalty() {
			return penalty;
		}
		public void setPenalty(double penalty) {
			this.penalty = penalty;
		}
		public int getLoan_days() {
			return loan_days;
		}
		public void setLoan_days(int loan_days) {
			this.loan_days = loan_days;
		}
		
		

}
