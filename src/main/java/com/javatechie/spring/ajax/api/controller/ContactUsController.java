package com.javatechie.spring.ajax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.ajax.api.entity.Contactus;
import com.javatechie.spring.ajax.api.service.ContactUsService;


@RestController
@RequestMapping("/contactUs")
public class ContactUsController {
	
	@Autowired
    private ContactUsService contactUsService;
	
	@PostMapping("/saveContactUs")
	public ResponseEntity<Object> saveContactUs(@RequestBody Contactus contactUs) {
		contactUsService.saveContactUs(contactUs);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}

}
