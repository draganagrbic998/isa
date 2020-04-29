package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.zahtevi.ZahtevRegistracija;
import com.example.demo.repository.KartonRepository;
import com.example.demo.repository.PacijentRepository;
import com.example.demo.repository.ZahtevRegistracijaRepository;

@Component
@Transactional(readOnly = true)
public class ZahtevRegistracijaService {

	@Autowired
	private ZahtevRegistracijaRepository zahtevRepository;

	@Autowired
	private PacijentRepository pacijentRepository;

	@Autowired
	private KartonRepository kartonRepository;
	
	@Transactional(readOnly = false)
	public void save(ZahtevRegistracija zahtev) {
		this.zahtevRepository.save(zahtev);
	}

	@Transactional(readOnly = true)
	public List<ZahtevRegistracija> findAll() {
		return this.zahtevRepository.findAll();
	}

	@Transactional(readOnly = true)
	public ZahtevRegistracija nadji(Integer id) {
		return zahtevRepository.getOne(id);
	}

	@Transactional(readOnly = false)
	public Pacijent potvrdi(ZahtevRegistracija zahtev) {
		
		Pacijent pacijent = new Pacijent(null, zahtev.getEmail(), zahtev.getLozinka(), 
				zahtev.getIme(), zahtev.getPrezime(), zahtev.getTelefon(), 
				zahtev.getDrzava(), zahtev.getGrad(), zahtev.getAdresa(), 
				false, true, null, 0);
		this.pacijentRepository.save(pacijent);
		Karton karton = new Karton(null, zahtev.getBrojOsiguranika(), 0, 0, 0, 0, null, pacijent);
		this.kartonRepository.save(karton);
		pacijent.setKarton(karton);
		this.pacijentRepository.save(pacijent);
		this.delete(zahtev.getId());
		return pacijent;
		
	}

	@Transactional(readOnly = false)
	public void delete(Integer id) {
		this.zahtevRepository.deleteById(id);
	}

}
