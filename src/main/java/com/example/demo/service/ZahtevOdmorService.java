package com.example.demo.service;

import java.util.Date;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Lekar;
import com.example.demo.model.ZahtevOdmor;
import com.example.demo.model.Zaposleni;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.ZahtevOdmorRepository;

@Component
@Transactional(readOnly = true)
public class ZahtevOdmorService {

	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private ZahtevOdmorRepository zahtevOdmorRepository;
	
	@Transactional(readOnly = false)
	public void save(ZahtevOdmor zahtev) {
		this.zahtevOdmorRepository.save(zahtev);
		Zaposleni z = zahtev.getZaposleni();
		z = (Zaposleni) Hibernate.unproxy(z);
		if (z instanceof Lekar) {
			Lekar l = (Lekar) z;
			l.setPoslednjaIzmena(new Date());
			this.lekarRepository.save(l);
		}
	}
	
}
