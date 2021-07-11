package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.CaptureKyc;


public interface CaptureKycRepository extends JpaRepository<CaptureKyc, Integer> {
	CaptureKyc findByuserId(int userId);

}
