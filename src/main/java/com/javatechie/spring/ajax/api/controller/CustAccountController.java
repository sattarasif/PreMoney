package com.javatechie.spring.ajax.api.controller;


import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.ajax.api.entity.CommonDBFields;
import com.javatechie.spring.ajax.api.entity.CustomerAccountsDetails;
import com.javatechie.spring.ajax.api.entity.LoanProductDtl;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustAccountDtlRepository;
import com.javatechie.spring.ajax.api.repository.LoanProductDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.CustAccountDtlService;


@RestController
@RequestMapping("/custaccount/api")
public class CustAccountController {
	
	@Autowired
    private CustAccountDtlService custAccountService;
	
	@Autowired
    private CustAccountDtlRepository custAccountDtlRepository;
	
	 @Autowired
	 private UsersRepository repository;
	 @Autowired
	 private LoanProductDtlRepository loanRepository;
	 
//	 @PersistenceContext
//	 private EntityManager entityManager;
	
	@PostMapping("/saveCustAccount")
	public ResponseEntity<Object> saveCustAccount(@RequestBody CustomerAccountsDetails custAccount) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = repository.findByphoneNumber(principal.getUsername());
		Date date  = new Date();
		user.setApprovalStatus("New Application");
		user.setCreated_date(date);
		repository.save(user);
		CustomerAccountsDetails updateCustAccount = custAccountService.getCustomerAccountByUserId(user.getId());
		if(updateCustAccount!=null)
			custAccount.setId(updateCustAccount.getId());
		custAccount.setUserId(user.getId());
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("New Application");
		commonField.setStatus("A");
		commonField.setCreatedDate(date);
		custAccount.setCommonDBFields(commonField);
		//custAccountService.saveCustomerAccountDtl(custAccount);
		custAccountDtlRepository.save(custAccount);
		return new ResponseEntity<Object>(calculateSummary(custAccount.getDurationInDays(), custAccount.getAppliedAmt()), HttpStatus.OK);
	}

	public SummaryResponse calculateSummary(int durationInDays, int appliedAmt) {
		
		DateTime date = new DateTime();
		
		LoanProductDtl loanProductDtl = loanRepository.findById(25001);
		double processingFees = loanProductDtl.getProcessing_fees() * appliedAmt * 0.01;
		double amtToBePaid = appliedAmt + ( (appliedAmt * loanProductDtl.getInterest_product() * 0.01 ) + processingFees );
		SummaryResponse response = new SummaryResponse();
		response.setDisbursedAmt(amtToBePaid+"");
		response.setInterestRate(loanProductDtl.getInterest_pa()+"%");
		response.setLoanAmt(appliedAmt+"");
		response.setLoanDuration(durationInDays+"");
		response.setProcessingFees(processingFees+"");
		response.setStartDate(getDateFromDateTime(date));
		response.setRepaymentDate(getDateFromDateTime(date.plusDays(durationInDays)));
		return response;
		
	}
	
	public String getDateFromDateTime(DateTime dateTime){
		  DateTime dt=new DateTime(dateTime);
		  return dt.toString(DateTimeFormat.forPattern("dd-MMM-yyyy"));
		  }
	
}
class SummaryResponse {
	
	private String loanAmt;
	private String loanDuration;
	private String processingFees;
	private String interestRate;
	private String disbursedAmt;
	private String repaymentDate;
	private String startDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(String loanAmt) {
		this.loanAmt = loanAmt;
	}
	public String getLoanDuration() {
		return loanDuration;
	}
	public void setLoanDuration(String loanDuration) {
		this.loanDuration = loanDuration;
	}
	public String getProcessingFees() {
		return processingFees;
	}
	public void setProcessingFees(String processingFees) {
		this.processingFees = processingFees;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getDisbursedAmt() {
		return disbursedAmt;
	}
	public void setDisbursedAmt(String disbursedAmt) {
		this.disbursedAmt = disbursedAmt;
	}
	public String getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	
	
}