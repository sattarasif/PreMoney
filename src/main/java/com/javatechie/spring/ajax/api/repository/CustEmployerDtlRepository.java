package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.CustomerEmployerDetails;


public interface CustEmployerDtlRepository extends JpaRepository<CustomerEmployerDetails, Integer> {
	CustomerEmployerDetails findByuserId(int userId);

}
