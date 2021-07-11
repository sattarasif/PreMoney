package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.LoanProductDtl;

public interface LoanProductDtlRepository extends JpaRepository<LoanProductDtl, Integer> {
	LoanProductDtl findById(int id);
}
