package com.javatechie.spring.ajax.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.Contactus;
import com.javatechie.spring.ajax.api.repository.ContactUsRepository;

@Service
public class ContactUsService {

	@Autowired
	private ContactUsRepository repository;
	
	public Contactus saveContactUs(Contactus contactUs) {
		return repository.save(contactUs);
	}
	public Contactus getContactUsById (int id) {
		return repository.findById(id).orElse(null);
	}
//	public Contactus getContactUsByPhoneNumber (String phoneNumber) {
//		return repository.findByphoneNumber(phoneNumber);
//	}
}
