package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.PanExtraction;
import com.javatechie.spring.ajax.api.repository.PanExtractionRepository;

@Service
public class PanExtractionService {

	@Autowired
	private PanExtractionRepository repository;
	
	public PanExtraction savePanExtraction(PanExtraction panExtraction) {
		return repository.save(panExtraction);
	}
	
	public List<PanExtraction> saveAddresses(List<PanExtraction> panExtraction) {
		return repository.saveAll(panExtraction);
	}
	public List<PanExtraction> getPanExtraction() {
		return repository.findAll();
	}
	public PanExtraction getPanExtractionById (int id) {
		return repository.findById(id).orElse(null);
	}
	public PanExtraction getAddressUserById (int id) {
	return repository.findByuserId(id);
}
	
	

}
