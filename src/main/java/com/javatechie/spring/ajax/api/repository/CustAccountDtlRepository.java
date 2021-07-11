package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.CustomerAccountsDetails;


public interface CustAccountDtlRepository extends JpaRepository<CustomerAccountsDetails, Integer> {
	CustomerAccountsDetails findByuserId(int userId);

}
