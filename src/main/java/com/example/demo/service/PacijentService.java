package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Pacijent;
import com.example.demo.repository.PacijentRepository;

@Component
public class PacijentService {

	@Autowired
	private PacijentRepository pacijentRepository;
	
	public void save(Pacijent pacijent) {
		this.pacijentRepository.save(pacijent);
	}

	public boolean aktiviraj(Integer id) {
		
		Pacijent pacijent = this.pacijentRepository.getOne(id);
		
		if (pacijent.isAktivan())
			return false;
		
		pacijent.setAktivan(true);
		this.pacijentRepository.save(pacijent);
		
		return true;
		
	}
	
}
