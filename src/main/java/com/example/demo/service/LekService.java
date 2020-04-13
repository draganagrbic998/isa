package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Lek;
import com.example.demo.model.StavkaSifrarnika;
import com.example.demo.repository.StavkaSifrarnikaRepository;

@Component
public class LekService {

	@Autowired
	private StavkaSifrarnikaRepository stavkaRepository;

	public void delete(Integer lekarId) {
		this.stavkaRepository.deleteById(lekarId);
	}

	public List<Lek> findAll() {
		List<Lek> lekovi = new ArrayList<Lek>();
		List<StavkaSifrarnika> stavke = this.stavkaRepository.findAll();
		
		for (StavkaSifrarnika ss : stavke) {
			if (ss.getClass().equals(Lek.class))
				lekovi.add((Lek) ss);
		}
		
		return lekovi;
	}

	public void save(Lek lek) {
		this.stavkaRepository.save(lek);
	}

}
