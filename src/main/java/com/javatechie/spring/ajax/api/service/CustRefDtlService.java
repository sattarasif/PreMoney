package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.CustomerReferenceDetails;
import com.javatechie.spring.ajax.api.repository.CustRefDtlRepository;

@Service
public class CustRefDtlService {
	
	@Autowired
	private CustRefDtlRepository repository;
	
	public CustomerReferenceDetails saveCustRefDtl(CustomerReferenceDetails custRefDtl) {
		return repository.save(custRefDtl);
	}
	
	public List<CustomerReferenceDetails> saveCustRefDtls(List<CustomerReferenceDetails> custRefDtl) {
		return repository.saveAll(custRefDtl);
	}
	public List<CustomerReferenceDetails> getCustRefDtls() {
		return repository.findAll();
	}
	public CustomerReferenceDetails getCustRefDtlById (int id) {
		return repository.findById(id).orElse(null);
	}
	public CustomerReferenceDetails getCustRefDtlByUserId (int id) {
		return repository.findByuserId(id);
	}

}
