package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.student1.KlinikaSlobodno;
import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.model.Karton;
import com.example.demo.model.Klinika;
import com.example.demo.model.Ocena;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.Sala;
import com.example.demo.model.StanjePosete;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.OcenaRepository;
import com.example.demo.repository.PosetaRepository;

@Component
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private PosetaRepository posetaRepository;
		
	public List<Klinika> findAll(){
		return this.klinikaRepository.findAll();
	}

	public void save(Klinika klinika) {
		this.klinikaRepository.save(klinika);
	}
	
	public Poseta oceni(Pacijent pacijent, OcenaParam param, Integer posetaId) {
		
		Klinika k = this.klinikaRepository.getOne(param.getId());
		Ocena o = k.refreshOcena(pacijent, param.getOcena());
		this.ocenaRepository.save(o);
		this.klinikaRepository.save(k);
		return this.posetaRepository.getOne(posetaId);
		
	}
	
	public List<Poseta> getPosete(Klinika klinika){
		
		List<Poseta> lista = new ArrayList<>();
		for (Poseta p: this.posetaRepository.findAll()) {
			for (Sala s: klinika.getSale()) {
				if (s.getId().equals(p.getSala().getId()))
					lista.add(p);
			}
		}
		return lista;
		
	}

	public List<KlinikaSlobodno> slobodno() {
		
		List<KlinikaSlobodno> lista = new ArrayList<>();
		for (Klinika k: this.klinikaRepository.findAll())
			lista.add(new KlinikaSlobodno(k, this.getPosete(k)));
		return lista;

	}

	public void zakazi(Integer posetaId, Karton karton) {
		
		Poseta p = this.posetaRepository.getOne(posetaId);
		p.setStanje(StanjePosete.ZAUZETO);
		p.setKarton(karton);
		this.posetaRepository.save(p);
		
	}

	public KlinikaSlobodno getKlinikaSlobodno(Integer posetaId) {

		Poseta p = this.posetaRepository.getOne(posetaId);
		Klinika k = p.getSala().getKlinika();
		return new KlinikaSlobodno(k, this.getPosete(k));

	}

	
}
