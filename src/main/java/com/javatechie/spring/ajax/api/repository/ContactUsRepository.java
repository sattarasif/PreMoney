package com.javatechie.spring.ajax.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javatechie.spring.ajax.api.entity.Contactus;


public interface ContactUsRepository extends JpaRepository<Contactus, Integer> {
	
}