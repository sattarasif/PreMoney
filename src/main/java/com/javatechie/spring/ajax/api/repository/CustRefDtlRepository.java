package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.CustomerReferenceDetails;


public interface CustRefDtlRepository extends JpaRepository<CustomerReferenceDetails, Integer> {
	CustomerReferenceDetails findByuserId(int userId);

}
