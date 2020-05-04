package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Sestra;
import com.example.demo.repository.SestraRepository;

@Component
@Transactional(readOnly = true)
public class SestraService {
	
	@Autowired
	private SestraRepository sestraRepository;
	
	@Transactional(readOnly = false)
	public void save(Sestra sestra) {
		this.sestraRepository.save(sestra);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		Sestra sestra = this.sestraRepository.getOne(id);
		sestra.setAktivan(false);
		this.sestraRepository.save(sestra);
	}
	
	@Transactional(readOnly = true)
	public List<Sestra> findAll(Admin admin) {
		List<Sestra> sestre = new ArrayList<>();
		for (Sestra s : this.sestraRepository.findByKlinikaId(admin.getKlinika().getId())) {
			if (s.isAktivan())
				sestre.add(s);
		}
		return sestre;
	}


}


