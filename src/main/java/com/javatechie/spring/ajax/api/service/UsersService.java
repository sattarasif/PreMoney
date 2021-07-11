package com.javatechie.spring.ajax.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository repository;
	
	public Users saveUser(Users user) {
		return repository.save(user);
	}
	
	public List<Users> saveUsers(List<Users> user) {
		return repository.saveAll(user);
	}
	public List<Users> getUsers() {
		return repository.findAll();
	}
	public Users getUserById (int id) {
		return repository.findById(id).orElse(null);
	}
	public Users getUserByName (String phoneNumber) {
		return repository.findByphoneNumber(phoneNumber);
	}
	
	public Users updateUsers (Users user) {
		 return repository.save(user);
	}
	
	public Page<Users> pagination(String search, Pageable pageable) {
		if (search != null) {
			return repository.findByapprovalStatus(search, pageable);
		}

		return repository.findAll(pageable);
	}
	
}
