package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Klinika;
import com.example.demo.repository.KlinikaRepository;

@Component
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	
	public List<Klinika> review(){
		return this.klinikaRepository.findAll();
	}

	//ovo radi admin
	public void save(Klinika klinika) {
		this.klinikaRepository.save(klinika);
	}
	
}
