package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.CustomerDetails;


public interface CustomerDtlRepository extends JpaRepository<CustomerDetails, Integer> {
	CustomerDetails findByuserId(int userId);

}
