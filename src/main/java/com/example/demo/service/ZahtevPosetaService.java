package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.ZahtevPosetaRepository;

@Component
@Transactional(readOnly = true)
public class ZahtevPosetaService {

	@Autowired
	private ZahtevPosetaRepository zahtevPosetaRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
		
	@Transactional(readOnly = false)
	public void save(ZahtevPoseta zahtev) {
		this.zahtevPosetaRepository.save(zahtev);
		Lekar l = zahtev.getLekar();
		l.setPoslednjaIzmena(new Date());
		this.lekarRepository.save(l);
	}
	
}
