package com.javatechie.spring.ajax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.ajax.api.entity.CommonDBFields;
import com.javatechie.spring.ajax.api.entity.CustomerBankDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustBankDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.CustBankDtlService;


@RestController
@RequestMapping("/bank/api")
public class CustBankController {
	
	@Autowired
    private CustBankDtlService bankService;
	
	@Autowired
    private CustBankDtlRepository custBankDtlRepository;
	
	@Autowired
	 private UsersRepository repository;
	
	@PostMapping("/saveCustBankDtl")
	public ResponseEntity<Object> saveCustAccount(@RequestBody CustomerBankDetails bankDtl) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = repository.findByphoneNumber(principal.getUsername());
		CustomerBankDetails updateBankDtl = bankService.getCustomerBankByUesrId(user.getId());
		if(updateBankDtl!=null) 
			bankDtl.setId(updateBankDtl.getId());
		bankDtl.setUserId(user.getId());
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("0");
		commonField.setStatus("A");
		bankDtl.setCommonDBFields(commonField);
		custBankDtlRepository.save(bankDtl);
		//bankService.saveCustomerBankDtl(bankDtl);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}

}
