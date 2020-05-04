package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Sala;
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
	
	@Transactional(readOnly = false)
	public Sala nadji(Integer id) {
		return this.salaRepository.getOne(id);
	}
	
	@Transactional(readOnly = true)
	public List<Sala> findAll(Zaposleni zaposleni) {
		List<Sala> sale = new ArrayList<>();
		for (Sala s : this.salaRepository.findByKlinikaId(zaposleni.getKlinika().getId())) {
			if (s.isAktivan())
				sale.add(s);
		}
		return sale;
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		
		Sala sala = this.salaRepository.getOne(id);
		for (Poseta p : sala.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				throw new MyRuntimeException();
		}
		sala.setAktivan(false);
		this.salaRepository.save(sala);
	}
	
	
	
	/*
	@Transactional(readOnly = false)
	public Date nadjiSlobodanTermin(Integer id) {
		Date danas = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		//this.pocetnoVreme = pocetno.substring(pocetno.length() - 5);
		
		Sala sala = this.salaRepository.getOne(id);
		for (Poseta p : sala.getPosete()) {
			//String pocetno = f.format(p.getDatum());
		}
		
	}
	*/
}
