package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.repository.PacijentRepository;

@Component
@Transactional(readOnly = true)
public class PacijentService {

	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Transactional(readOnly = false)
	public void save(Pacijent pacijent) {
		this.pacijentRepository.save(pacijent);
	}

	@Transactional(readOnly = false)
	public boolean aktiviraj(Integer id) {
		
		Pacijent pacijent = this.pacijentRepository.getOne(id);
		
		if (pacijent.isAktivan())
			return false;
		
		pacijent.setAktivan(true);
		this.pacijentRepository.save(pacijent);
		
		return true;	
	}
	
	
	
	
	
}
