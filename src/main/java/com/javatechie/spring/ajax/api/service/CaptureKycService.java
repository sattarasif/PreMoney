package com.javatechie.spring.ajax.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.CaptureKyc;
import com.javatechie.spring.ajax.api.repository.CaptureKycRepository;
@Service
public class CaptureKycService {

	@Autowired
	private CaptureKycRepository repository;
	
	public CaptureKyc saveCaptureKyc(CaptureKyc caputureKyc) {
		return repository.save(caputureKyc);
	}
	public CaptureKyc getCaputureKycByUserId (int id) {
		return repository.findByuserId(id);
	}
}
