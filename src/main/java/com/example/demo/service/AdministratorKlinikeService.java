package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.repository.LokacijaRepository;
import com.example.demo.repository.KorisnikRepository;


@Component
public class AdministratorKlinikeService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	
	//ovo radi admin
	public void create(Admin admin) {
		this.lokacijaRepository.save(admin.getLokacija());
		this.korisnikRepository.save(admin);
	}
}
