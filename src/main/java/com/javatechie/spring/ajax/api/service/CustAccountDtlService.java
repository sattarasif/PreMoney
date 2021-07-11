package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.CustomerAccountsDetails;
import com.javatechie.spring.ajax.api.repository.CustAccountDtlRepository;
@Service
public class CustAccountDtlService {
	
	@Autowired
	private CustAccountDtlRepository repository;
	
	public CustomerAccountsDetails saveCustomerAccountDtl(CustomerAccountsDetails custAccount) {
		return repository.save(custAccount);
	}
	
	public List<CustomerAccountsDetails> saveCustomerAccountDtls(List<CustomerAccountsDetails> custAccount) {
		return repository.saveAll(custAccount);
	}
	public List<CustomerAccountsDetails> getCustomerAccountDtls() {
		return repository.findAll();
	}
	public CustomerAccountsDetails getCustomerAccountById (int id) {
		return repository.findById(id).orElse(null);
	}
	public CustomerAccountsDetails getCustomerAccountByUserId (int id) {
		return repository.findByuserId(id);
	}

}
