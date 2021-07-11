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
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.AddressDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.AddressDtlService;


@RestController
@RequestMapping("/address/api")
public class AddressController {
	
	@Autowired
    private AddressDtlService addressService;
	
	@Autowired
    private AddressDtlRepository addressDtlRepository;
	
	 @Autowired
	 private UsersRepository repository;
	
	@PostMapping("/saveAddress")
	public ResponseEntity<Object> saveAddress(@RequestBody AddressDetails address) {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = repository.findByphoneNumber(principal.getUsername());
		AddressDetails updateAddress= addressService.getAddressUserById(user.getId());
		if(updateAddress!=null)
			address.setId(updateAddress.getId());
		address.setUserId(user.getId());
		address.setAddressType("CUSTOMER");
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("0");
		commonField.setStatus("A");
		address.setCommonDBFields(commonField);		
		//addressService.saveAddress(address);
		addressDtlRepository.save(address);
		return new ResponseEntity<Object>("OK", HttpStatus.OK);
	}

}
