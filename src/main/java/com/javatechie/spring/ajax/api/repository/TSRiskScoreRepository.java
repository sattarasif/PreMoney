package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.TSRiskScore;

public interface TSRiskScoreRepository extends JpaRepository<TSRiskScore, Integer> {
	TSRiskScore findByuserId(int userId);

}
