package com.javatechie.spring.ajax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.ajax.api.entity.AddressDetails;
import com.javatechie.spring.ajax.api.entity.CommonDBFields;
import com.javatechie.spring.ajax.api.entity.CustomerEmployerDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustEmployerDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.CustEmployerDtlService;


@RestController
@RequestMapping("/employer/api")
public class CustEmployerController {
	
	@Autowired
    private CustEmployerDtlService employerService;
	
	@Autowired
    private CustEmployerDtlRepository custEmployerDtlRepository;
	
	@Autowired
	private UsersRepository repository;
	
	@PostMapping("/saveEmployer")
	public ResponseEntity<Object> saveCustAccount(@RequestBody CustomerEmployerDetails employeeDtl) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = repository.findByphoneNumber(principal.getUsername());
		CustomerEmployerDetails UpdateEmployeeDtl = employerService.getCustomerEmployerByUserId(user.getId());
		if(UpdateEmployeeDtl!=null)
			employeeDtl.setId(UpdateEmployeeDtl.getId());
		employeeDtl.setUserId(user.getId());
		AddressDetails address = new AddressDetails();
		address.setAddressType("EMPLOYER");
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("0");
		commonField.setStatus("A");
		address.setCommonDBFields(commonField);	
		
		employeeDtl.setCommonDBFields(commonField);
		custEmployerDtlRepository.save(employeeDtl);
		//employerService.saveCustomerEmployerDtl(employeeDtl);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}

}
