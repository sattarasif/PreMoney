package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.KYCDetails;


public interface KYCDtlRepository extends JpaRepository<KYCDetails, Integer> {
	KYCDetails findByuserId(int userId);

}
