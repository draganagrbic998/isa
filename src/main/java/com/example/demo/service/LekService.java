package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Lek;
import com.example.demo.repository.LekRepository;

@Component
public class LekService {

	@Autowired
	private LekRepository lekRepository;

	public void delete(Integer lekId) {
		this.lekRepository.deleteById(lekId);
	}

	public List<Lek> findAll() {
		return this.lekRepository.findAll();
	}

	public void save(Lek lek) {
		this.lekRepository.save(lek);
	}

}
