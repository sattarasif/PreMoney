package com.javatechie.spring.ajax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.ajax.api.entity.CommonDBFields;
import com.javatechie.spring.ajax.api.entity.CustomerDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustomerDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.CustomUserDetailsService;
import com.javatechie.spring.ajax.api.service.CustomerDtlService;
import com.javatechie.spring.ajax.api.service.UsersService;


@RestController
@RequestMapping("/customer/api")
public class CustomerController {

	@Autowired
    private CustomerDtlService customerService;
	
	@Autowired
    private CustomerDtlRepository customerDtlRepository;
    
    @Autowired
    private UsersService usersService;
	
	 @Autowired
	 private UsersRepository repository;
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<Object> saveCustAccount(@RequestBody CustomerDetails customerDtl) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = repository.findByphoneNumber(principal.getUsername());
		CustomerDetails updateCustomerDtl = customerService.getCustomerByUserId(user.getId());
		if(updateCustomerDtl!=null)
			customerDtl.setId(updateCustomerDtl.getId());
		user.setEmail(customerDtl.getEmailAddress());
		user.setName(customerDtl.getFullName());
		customerDtl.setUserId(user.getId());
		usersService.updateUsers(user);
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("0");
		commonField.setStatus("A");
		customerDtl.setCommonDBFields(commonField);
		customerDtlRepository.save(customerDtl);
		//customerService.saveCustomerDtl(customerDtl);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}
}
