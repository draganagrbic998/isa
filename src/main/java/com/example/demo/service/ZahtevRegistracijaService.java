package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.repository.ZahtevRegistracijaRepository;

@Component
public class ZahtevRegistracijaService {

	@Autowired
	private ZahtevRegistracijaRepository zahtevRepository;
	
	public void save(ZahtevRegistracija zahtev) {
		this.zahtevRepository.save(zahtev);
	}
	
}
