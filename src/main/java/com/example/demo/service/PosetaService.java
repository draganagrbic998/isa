package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Karton;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.PosetaRepository;

@Component
@Transactional(readOnly = true)
public class PosetaService {
	
	@Autowired
	private PosetaRepository posetaRepository;
	
	@Autowired
	private LekarRepository lekarRepository;

	@Transactional(readOnly = false)
	public void save(Poseta poseta) {
		this.posetaRepository.save(poseta);
		for (Lekar l: poseta.getLekari()) {
			l.setPoslednjaIzmena(new Date());
			this.lekarRepository.save(l);
		}
		
	}

	@Transactional(readOnly = false)
	public void deleteById(Integer posetaId) {
		this.posetaRepository.deleteById(posetaId);
	}

	@Transactional(readOnly = false)
	public void zakazi(Integer posetaId, Karton karton) {
		
		Poseta p = this.posetaRepository.getOne(posetaId);
		if (!p.getStanje().equals(StanjePosete.SLOBODNO))
			throw new RuntimeException();
		p.setStanje(StanjePosete.ZAUZETO);
		p.setKarton(karton);
		this.posetaRepository.save(p);
		
	}

	@Transactional(readOnly = false)
	public Poseta otkazi(Integer id) {
		Poseta poseta = this.posetaRepository.getOne(id);
		this.posetaRepository.deleteById(id);
		return poseta;
		
	}

}
