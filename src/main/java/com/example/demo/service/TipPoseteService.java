package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.TipPosete;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class TipPoseteService {

	@Autowired
	TipPoseteRepository tpRepository;
	
	public void save(TipPosete tipPosete) {
		this.tpRepository.save(tipPosete);
	}
}
