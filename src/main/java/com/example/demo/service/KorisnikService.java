package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Component
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public void save(Korisnik korisnik) {
		this.korisnikRepository.save(korisnik);
	}
	
	public Korisnik getOne(Integer id) {
		return this.korisnikRepository.getOne(id);
	}

}
