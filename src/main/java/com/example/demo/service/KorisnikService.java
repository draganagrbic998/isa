package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.User;
import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Component
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public Korisnik prijava(User user) {
		for (Korisnik k: this.korisnikRepository.findAll()) {
			if (k.getEmail().equals(user.getEmail()) && k.getLozinka().equals(user.getLozinka()))
				return k;
		}
		return null;
		
	}
	
	//najbolej svugde nazovite metode save (umesto create ili update)
	public void save(Korisnik korisnik) {
		this.korisnikRepository.save(korisnik);
	}
	
	public Korisnik getOne(Integer id) {
		return this.korisnikRepository.getOne(id);
	}

}
