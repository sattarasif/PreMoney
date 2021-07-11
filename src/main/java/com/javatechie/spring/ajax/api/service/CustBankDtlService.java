package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.CustomerBankDetails;
import com.javatechie.spring.ajax.api.repository.CustBankDtlRepository;
@Service
public class CustBankDtlService {
	
	@Autowired
	private CustBankDtlRepository repository;
	
	public CustomerBankDetails saveCustomerBankDtl(CustomerBankDetails customerBankDtl) {
		return repository.save(customerBankDtl);
	}
	
	public List<CustomerBankDetails> saveCustomerBankDtls(List<CustomerBankDetails> customerBankDtls) {
		return repository.saveAll(customerBankDtls);
	}
	public List<CustomerBankDetails> getCustomerBankDtls() {
		return repository.findAll();
	}
	public CustomerBankDetails getCustomerBankById (int id) {
		return repository.findById(id).orElse(null);
	}
	public CustomerBankDetails getCustomerBankByUesrId (int id) {
		return repository.findByuserId(id);
	}

}
