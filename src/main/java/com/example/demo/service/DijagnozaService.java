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

	public void save(Dijagnoza dijagnoza) {
		this.dijagnozaRepository.save(dijagnoza);
	}
	
	public void delete(Integer id) {
		this.dijagnozaRepository.deleteById(id);
	}

	public List<Dijagnoza> findAll() {
		return this.dijagnozaRepository.findAll();
	}

}
