package com.example.demo.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Component
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public String save(KorisnikDTO korisnikDTO) {
		Korisnik korisnik = this.korisnikRepository.getOne(korisnikDTO.getId());
		korisnik.setLozinka(korisnikDTO.getLozinka());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setTelefon(korisnikDTO.getTelefon());
		korisnik.setDrzava(korisnikDTO.getDrzava());
		korisnik.setGrad(korisnikDTO.getGrad());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		this.korisnikRepository.save(korisnik);
		return Hibernate.unproxy(korisnik).getClass().getSimpleName().toLowerCase();
	}
	
	
}
