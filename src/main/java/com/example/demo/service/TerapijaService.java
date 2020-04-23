package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Sestra;
import com.example.demo.model.Terapija;
import com.example.demo.repository.TerapijaRepository;

@Component
public class TerapijaService {

	@Autowired
	private TerapijaRepository terapijaRepository;
	
	public void save(Terapija terapija) {
		this.terapijaRepository.save(terapija);
	}

	public List<Terapija> nadjiNeoverene(Sestra sestra) {
		List<Terapija> terapije = new ArrayList<>();
		
		for (Terapija t : this.terapijaRepository.findAll()) {
			if (t.getSestra() == null && t.getIzvestaj().getPoseta().getSala().getKlinika().equals(sestra.getKlinika())) 
				terapije.add(t);

		}
		
		return terapije;
	}

	public boolean overi(Integer id, Sestra sestra) {
		
		Terapija terapija = this.terapijaRepository.getOne(id);
		
		if (terapija.getSestra() != null)
			return false;
		
		terapija.setSestra(sestra);
		this.terapijaRepository.save(terapija);
		
		return true;
		
	}

}
