package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.AddressDetails;

public interface AddressDtlRepository extends JpaRepository<AddressDetails, Integer> {
	AddressDetails findByuserId(int userId);

}
