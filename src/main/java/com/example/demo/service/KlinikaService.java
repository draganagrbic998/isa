package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.model.Klinika;
import com.example.demo.model.Ocena;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
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
	
}
