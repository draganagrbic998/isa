package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Dijagnoza;
import com.example.demo.model.StavkaSifrarnika;
import com.example.demo.repository.StavkaSifrarnikaRepository;

@Component
public class DijagnozaService {

	@Autowired
	private StavkaSifrarnikaRepository stavkaRepository;

	public void delete(Integer dijagnozaId) {
		this.stavkaRepository.deleteById(dijagnozaId);
	}

	public List<Dijagnoza> findAll() {
		List<Dijagnoza> dijagnoze = new ArrayList<Dijagnoza>();
		List<StavkaSifrarnika> stavke = this.stavkaRepository.findAll();
		
		for (StavkaSifrarnika ss : stavke) {
			if (ss.getClass().equals(Dijagnoza.class))
				dijagnoze.add((Dijagnoza) ss);
		}
		
		return dijagnoze;
	}

	public void save(Dijagnoza dijagnoza) {
		this.stavkaRepository.save(dijagnoza);
	}

}
