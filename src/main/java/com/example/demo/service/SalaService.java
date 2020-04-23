package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Sala;
import com.example.demo.repository.SalaRepository;

@Component
@Transactional(readOnly = true)
public class SalaService {

	@Autowired 
	private SalaRepository salaRepository;
	
	@Transactional(readOnly = false)
	public void save(Sala sala) {
		this.salaRepository.save(sala);
	}
}
