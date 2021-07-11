package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javatechie.spring.ajax.api.entity.LoanProductDtl;
import com.javatechie.spring.ajax.api.repository.LoanProductDtlRepository;

public class LoanProductDtlService {
	
	@Autowired
	private LoanProductDtlRepository repository;
	
	public LoanProductDtl saveLoanProductDtl(LoanProductDtl loanProductDtl) {
		return repository.save(loanProductDtl);
	}
	
	public List<LoanProductDtl> saveLoanProductDtls(List<LoanProductDtl> LoanProductDtls) {
		return repository.saveAll(LoanProductDtls);
	}
	public List<LoanProductDtl> getLoanProductDtls() {
		return repository.findAll();
	}
	public LoanProductDtl getCLoanProductDtlById (int id) {
		return repository.findById(id);
	}
}
