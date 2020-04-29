package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ZahtevOdmorDTO;
import com.example.demo.model.Klinika;
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
	
	@Transactional(readOnly = true)
	public ZahtevOdmor nadji(Integer id) {
		return this.zahtevOdmorRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		this.zahtevOdmorRepository.deleteById(id);
	}
	
	public List<ZahtevOdmorDTO> findAll(Klinika klinika) {
		List<ZahtevOdmorDTO> zahtevi = new ArrayList<>();
		for (ZahtevOdmor z : this.zahtevOdmorRepository.findAll()) {
			if (z.getKlinika().getId().equals(klinika.getId()) && !z.getOdobren())
				zahtevi.add(new ZahtevOdmorDTO(z));
		}
		return zahtevi;
	}

}
