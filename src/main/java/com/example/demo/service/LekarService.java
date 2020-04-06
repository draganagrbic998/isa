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
	
	
	
//	public boolean obrisiLekara(String email) {
//		Integer id = null;
//		for (Lekar lekar : lekarRepository.findAll()) {
//			if (lekar.getEmail().equals(email)) {
//				id = lekar.getId();
//				break;
//			}
//		}
//		if (id != null) {
//			lekarRepository.deleteById(id);
//			lekarRepository.flush();
//			return true;
//		}
//		return false;
//	}
	
	//ovo radi admin
	public void create(Lekar lekar) {
		this.lokacijaRepository.save(lekar.getLokacija());
		this.korisnikRepository.save(lekar);
	}
}
