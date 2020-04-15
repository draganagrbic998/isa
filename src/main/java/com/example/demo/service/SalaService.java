package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Sala;
import com.example.demo.repository.SalaRepository;

@Component
public class SalaService {

	@Autowired 
	SalaRepository salaRepository;
	
	public void save(Sala sala) {
		this.salaRepository.save(sala);
	}
}
