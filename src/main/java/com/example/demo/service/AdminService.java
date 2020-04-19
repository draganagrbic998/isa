package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.repository.AdminRepository;

@Component
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public void save(Admin admin) {
		this.adminRepository.save(admin);
	}
	
}
