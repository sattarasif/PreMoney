package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.CustomerEmployerDetails;
import com.javatechie.spring.ajax.api.repository.CustEmployerDtlRepository;

@Service
public class CustEmployerDtlService {
	
	@Autowired
	private CustEmployerDtlRepository repository;
	
	public CustomerEmployerDetails saveCustomerEmployerDtl(CustomerEmployerDetails customerEmployer) {
		return repository.save(customerEmployer);
	}
	
	public List<CustomerEmployerDetails> saveCustomerEmployerDtls(List<CustomerEmployerDetails> customerEmployers) {
		return repository.saveAll(customerEmployers);
	}
	public List<CustomerEmployerDetails> getCustomerEmployerDtls() {
		return repository.findAll();
	}
	public CustomerEmployerDetails getCustomerEmployerById (int id) {
		return repository.findById(id).orElse(null);
	}
	public CustomerEmployerDetails getCustomerEmployerByUserId (int id) {
		return repository.findByuserId(id);
	}
}
