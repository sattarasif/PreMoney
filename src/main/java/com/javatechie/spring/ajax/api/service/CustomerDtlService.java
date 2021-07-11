package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.CustomerDetails;
import com.javatechie.spring.ajax.api.repository.CustomerDtlRepository;

@Service
public class CustomerDtlService {
	
	@Autowired
	private CustomerDtlRepository repository;
	
	public CustomerDetails saveCustomerDtl(CustomerDetails customerDtl) {
		return repository.save(customerDtl);
	}
	
	public List<CustomerDetails> saveCustomerDtls(List<CustomerDetails> customerDtls) {
		return repository.saveAll(customerDtls);
	}
	public List<CustomerDetails> getCustomerDtls() {
		return repository.findAll();
	}
	public CustomerDetails getCustomerById (int id) {
		return repository.findById(id).orElse(null);
	}
	
	public CustomerDetails getCustomerByUserId (int id) {
		return repository.findByuserId(id);
	}
	


}
