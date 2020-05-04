package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.example.demo.dto.unos.ZahtevPosetaObradaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.resursi.Klinika;
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
		if (!l.slobodan(zahtev.pocetak(), zahtev.kraj())) {
			System.out.println("Lekar nije slobodan!");
			throw new MyRuntimeException();
		}
		l.setPoslednjaIzmena(new Date());
		this.lekarRepository.save(l);
	}
	
	public List<ZahtevPosetaObradaDTO> findAll(Klinika klinika) {
		List<ZahtevPosetaObradaDTO> zahtevi = new ArrayList<>();
		for (ZahtevPoseta z : this.zahtevPosetaRepository.findAll()) {
			if (z.getTipPosete().getKlinika().getId().equals(klinika.getId()) && z.getTipPosete().getPregled())
				zahtevi.add(new ZahtevPosetaObradaDTO(z));
		}
		return zahtevi;
	}
	
	@Transactional(readOnly = true)
	public List<ZahtevPosetaObradaDTO> getOperacije(Klinika klinika) {
		List<ZahtevPosetaObradaDTO> zahtevi = new ArrayList<>();
		for (ZahtevPoseta z : this.zahtevPosetaRepository.findAll()) {
			if (z.getTipPosete().getKlinika().getId().equals(klinika.getId()) && !z.getTipPosete().getPregled())
				zahtevi.add(new ZahtevPosetaObradaDTO(z));
		}
		return zahtevi;
	}

	@Transactional(readOnly = false)
	public void obrisi(Integer id) {
		this.zahtevPosetaRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	public ZahtevPoseta nadji(Integer id) {
		return this.zahtevPosetaRepository.getOne(id);
	}
}
