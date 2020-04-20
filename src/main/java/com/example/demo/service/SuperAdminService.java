package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.SuperAdmin;
import com.example.demo.repository.SuperAdminRepository;

@Component
public class SuperAdminService {
	
	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	public void save(SuperAdmin superAdmin) {
		this.superAdminRepository.save(superAdmin);
	}
	
}
