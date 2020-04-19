package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.ZahtevPoseta;
import com.example.demo.repository.ZahtevPosetaRepository;

@Component
public class ZahtevPosetaService {

	@Autowired
	private ZahtevPosetaRepository zahtevPosetaRepository;
	
	public void save(ZahtevPoseta zahtev) {
		this.zahtevPosetaRepository.save(zahtev);
	}
	
}
