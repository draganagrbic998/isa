package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.repository.LokacijaRepository;
import com.example.demo.repository.ZahtevRegistracijaRepository;

@Component
public class ZahtevRegistracijaService {

	@Autowired
	private ZahtevRegistracijaRepository zahtevRepository;
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	//ovo ce raditi neregistrovani korisnik
	public void create(ZahtevRegistracija zahtev) {
		this.lokacijaRepository.save(zahtev.getNovaLokacija());
		this.zahtevRepository.save(zahtev);
	}

	//kasnije treba da dodam brisanje (super admin ce to raditi)
	//i pregled (isto ce to super admin raditi)
	
}