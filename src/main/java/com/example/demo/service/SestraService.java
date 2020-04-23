package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.model.Sestra;

import com.example.demo.repository.SestraRepository;

@Component
public class SestraService {
	
	@Autowired
	private SestraRepository sestraRepository;
	
	public void save(Sestra sestra) {
		this.sestraRepository.save(sestra);
	}
	
	public void delete(Integer id) {
		Sestra sestra = this.sestraRepository.getOne(id);
		sestra.setAktivan(false);
		this.sestraRepository.save(sestra);
	}
	
	public List<Sestra> findAll(Admin admin) {
		List<Sestra> sestre = new ArrayList<>();
		for (Sestra s : this.sestraRepository.findAll()) {
			if (s.getKlinika().getId().equals(admin.getKlinika().getId()) && s.isAktivan())
				sestre.add(s);
		}
		return sestre;
	}

}


