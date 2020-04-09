package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Lekar;
import com.example.demo.repository.KorisnikRepository;


@Component
public class LekarService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	
	public void delete(Integer lekarId) {
		this.korisnikRepository.deleteById(lekarId);
	}
	
	//ovo radi admin
	public void save(Lekar lekar) {
		this.korisnikRepository.save(lekar);
	}
}
