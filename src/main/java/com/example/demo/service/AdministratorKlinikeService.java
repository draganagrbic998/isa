package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.repository.KorisnikRepository;


@Component
public class AdministratorKlinikeService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	
	
	//ovo radi admin
	public void create(Admin admin) {
		this.korisnikRepository.save(admin);
	}
}
