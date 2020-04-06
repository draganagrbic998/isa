package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Klinika;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LokacijaRepository;

@Component
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	public List<Klinika> review(){
		return this.klinikaRepository.findAll();
	}

	//ovo radi admin
	public void create(Klinika klinika) {
		this.lokacijaRepository.save(klinika.getLokacija());
		this.klinikaRepository.save(klinika);
	}
	
}
