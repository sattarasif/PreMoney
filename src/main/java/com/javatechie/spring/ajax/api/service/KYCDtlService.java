package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.KYCDetails;
import com.javatechie.spring.ajax.api.repository.KYCDtlRepository;

@Service
public class KYCDtlService {
	
	@Autowired
	private KYCDtlRepository repository;
	
	public KYCDetails saveKYCDtl(KYCDetails kycDtl) {
		return repository.save(kycDtl);
	}
	
	public List<KYCDetails> saveKYCDtls(List<KYCDetails> kycDtls) {
		return repository.saveAll(kycDtls);
	}
	public List<KYCDetails> getKYCDtls() {
		return repository.findAll();
	}
	public KYCDetails getKycDtlById (int id) {
		return repository.findById(id).orElse(null);
	}
	public KYCDetails getKycDtlByUserId (int id) {
		return repository.findByuserId(id);
	}
}
