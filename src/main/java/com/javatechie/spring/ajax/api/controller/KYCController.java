package com.javatechie.spring.ajax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.ajax.api.entity.KYCDetails;
import com.javatechie.spring.ajax.api.service.KYCDtlService;


@RestController
@RequestMapping("/kyc/api")
public class KYCController {
	
	@Autowired
    private KYCDtlService kycService;
	
	@PostMapping("/saveKyc")
	public ResponseEntity<Object> saveCustAccount(@RequestBody KYCDetails kycDtl) {
		kycService.saveKYCDtl(kycDtl);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}

}
