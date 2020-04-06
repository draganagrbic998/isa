package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Lekar;
import com.example.demo.repository.LokacijaRepository;
import com.example.demo.repository.KorisnikRepository;


@Component
public class LekarService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	
	
	public void delete(Integer lekarId) {
		this.korisnikRepository.deleteById(lekarId);
	}
	
	//ovo radi admin
	public void create(Lekar lekar) {
		this.lokacijaRepository.save(lekar.getLokacija());
		this.korisnikRepository.save(lekar);
	}
}
