package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.PanExtraction;


public interface PanExtractionRepository extends JpaRepository<PanExtraction, Integer> {
	PanExtraction findByuserId(int userId);

}

