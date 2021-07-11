package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.CustomerBankDetails;


public interface CustBankDtlRepository extends JpaRepository<CustomerBankDetails, Integer> {
	CustomerBankDetails findByuserId(int userId);

}
