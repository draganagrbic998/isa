package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.ZahtevPregled;
import com.example.demo.repository.ZahtevPregledRepository;

@Component
public class ZahtevPregledService {

	@Autowired
	private ZahtevPregledRepository zahtevPregledRepository;
	
	public void save(ZahtevPregled zahtev) {
		this.zahtevPregledRepository.save(zahtev);
	}
	
}
