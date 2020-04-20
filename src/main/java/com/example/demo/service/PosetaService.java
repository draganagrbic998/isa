package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Karton;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.repository.PosetaRepository;

@Component
public class PosetaService {
	
	@Autowired
	private PosetaRepository posetaRepository;

	public void save(Poseta poseta) {
		this.posetaRepository.save(poseta);
	}

	public void deleteById(Integer posetaId) {
		this.posetaRepository.deleteById(posetaId);
	}

	public void zakazi(Integer posetaId, Karton karton) {
		
		Poseta p = this.posetaRepository.getOne(posetaId);
		p.setStanje(StanjePosete.ZAUZETO);
		p.setKarton(karton);
		this.posetaRepository.save(p);
		
	}

	public Poseta otkazi(Integer id) {
		Poseta poseta = this.posetaRepository.getOne(id);
		this.posetaRepository.deleteById(id);
		return poseta;
		
	}

}
