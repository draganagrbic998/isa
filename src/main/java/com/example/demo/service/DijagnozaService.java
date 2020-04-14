package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Dijagnoza;
import com.example.demo.repository.DijagnozaRepository;

@Component
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository dijagnozaRepository;

	public void delete(Integer dijagnozaId) {
		this.dijagnozaRepository.deleteById(dijagnozaId);
	}

	public List<Dijagnoza> findAll() {
		return this.dijagnozaRepository.findAll();
	}

	public void save(Dijagnoza dijagnoza) {
		this.dijagnozaRepository.save(dijagnoza);
	}

}
