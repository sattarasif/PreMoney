package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.TSRiskScore;
import com.javatechie.spring.ajax.api.repository.TSRiskScoreRepository;

@Service
public class TSRiskScoreService {

	@Autowired
	private TSRiskScoreRepository repository;
	
	public TSRiskScore saveTSRiskScore(TSRiskScore tSRiskScore) {
		return repository.save(tSRiskScore);
	}
	
	public List<TSRiskScore> getTSRiskScore() {
		return repository.findAll();
	}
	public TSRiskScore TSRiskScoreById (int id) {
		return repository.findById(id).orElse(null);
	}
	public TSRiskScore getTSRiskScoreUserById (int id) {
	return repository.findByuserId(id);
}
	
}
