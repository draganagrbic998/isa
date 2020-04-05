package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Lekar;
import com.example.demo.model.Klinika;
import com.example.demo.repository.LokacijaRepository;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LekarRepository;


@Component
public class LekarService {
	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	//dobavlja listu klinika
	public List<Klinika> getKlinike() {
		return klinikaRepository.findAll();
	}
	
	public boolean obrisiLekara(String email) {
		Integer id = null;
		for (Lekar lekar : lekarRepository.findAll()) {
			if (lekar.getEmail().equals(email)) {
				id = lekar.getId();
				break;
			}
		}
		if (id != null) {
			lekarRepository.deleteById(id);
			lekarRepository.flush();
			return true;
		}
		return false;
	}
	
	//ovo radi admin
	public void create(Lekar lekar) {
		this.lokacijaRepository.save(lekar.getLokacija());
		this.lekarRepository.save(lekar);
	}
}
