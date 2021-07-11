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
import com.javatechie.spring.ajax.api.entity.CustomerReferenceDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustRefDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.CustRefDtlService;


@RestController
@RequestMapping("/custref/api")
public class CustRefController {
	
	@Autowired
    private CustRefDtlService custRefService;

	@Autowired
    private CustRefDtlRepository custRefDtlRepository;
	
	@Autowired
	 private UsersRepository repository;

	@PostMapping("/saveCustRef")
	public ResponseEntity<Object> saveCustAccount(@RequestBody CustomerReferenceDetails custRefDtl) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = repository.findByphoneNumber(principal.getUsername());
		CustomerReferenceDetails updateCustRefDtl = custRefService.getCustRefDtlByUserId(user.getId());
		if(updateCustRefDtl!=null)
			custRefDtl.setId(updateCustRefDtl.getId());
		custRefDtl.setUserId(user.getId());
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("0");
		commonField.setStatus("A");
		custRefDtl.setCommonDBFields(commonField);
		custRefDtlRepository.save(custRefDtl);
		//custRefService.saveCustRefDtl(custRefDtl);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}
}
