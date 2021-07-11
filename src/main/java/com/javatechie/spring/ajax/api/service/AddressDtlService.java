package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.AddressDetails;
import com.javatechie.spring.ajax.api.repository.AddressDtlRepository;

@Service
public class AddressDtlService {
	
	@Autowired
	private AddressDtlRepository repository;
	
	public AddressDetails saveAddress(AddressDetails address) {
		return repository.save(address);
	}
	
	public List<AddressDetails> saveAddresses(List<AddressDetails> address) {
		return repository.saveAll(address);
	}
	public List<AddressDetails> getAddress() {
		return repository.findAll();
	}
	public AddressDetails getAddressById (int id) {
		return repository.findById(id).orElse(null);
	}
	public AddressDetails getAddressUserById (int id) {
	return repository.findByuserId(id);
}
	
	public AddressDetails updateAddress (AddressDetails address) {
		AddressDetails existingAddress = null;
		return null;
	}

}
