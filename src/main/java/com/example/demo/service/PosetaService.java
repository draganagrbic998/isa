package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Poseta;
import com.example.demo.repository.PosetaRepository;

@Component
public class PosetaService {
	
	@Autowired
	private PosetaRepository posetaRepository;

	public Poseta getOne(Integer posetaId) {
		// TODO Auto-generated method stub
		return this.posetaRepository.getOne(posetaId);
	}

	public void save(Poseta poseta) {
		// TODO Auto-generated method stub
		this.posetaRepository.save(poseta);
	}

	public void deleteById(Integer posetaId) {
		// TODO Auto-generated method stub
		this.posetaRepository.deleteById(posetaId);
	}

}
