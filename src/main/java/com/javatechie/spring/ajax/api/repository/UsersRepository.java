package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByphoneNumber(String phoneNo);
	
	Page<Users> findByapprovalStatus(String status, Pageable pageable);
}
