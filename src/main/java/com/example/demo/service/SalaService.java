package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Poseta;
import com.example.demo.model.Sala;
import com.example.demo.model.StanjePosete;
import com.example.demo.model.Zaposleni;
import com.example.demo.repository.SalaRepository;

@Component
@Transactional(readOnly = true)
public class SalaService {

	@Autowired 
	private SalaRepository salaRepository;
	
	@Transactional(readOnly = false)
	public void save(Sala sala) {
		this.salaRepository.save(sala);
	}
	@Transactional(readOnly = true)
	public List<Sala> findAll(Zaposleni zaposleni) {
		List<Sala> sale = new ArrayList<>();
		for (Sala s : this.salaRepository.findAll()) {
			if (s.getKlinika().getId().equals(zaposleni.getKlinika().getId()) && s.isAktivan())
				sale.add(s);
		}
		return sale;
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		
		Sala sala = this.salaRepository.getOne(id);
		for (Poseta p: sala.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				throw new RuntimeException();
		}
		sala.setAktivan(false);
		this.salaRepository.save(sala);
		
		
	}
}
