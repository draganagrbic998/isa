package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.TipPosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
@Transactional(readOnly = true)
public class TipPoseteService {

	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
		
	@Transactional(readOnly = false)
	public void save(TipPosete tipPosete) {
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getNaziv().equals(tipPosete.getNaziv()) && tp.getKlinika().getId().
					equals(tipPosete.getKlinika().getId()))
				throw new MyRuntimeException();
		}
		this.tipPoseteRepository.save(tipPosete);
	}
	
	@Transactional(readOnly = false)
	public void saveChanges(TipPosete tipPosete) {
		this.tipPoseteRepository.save(tipPosete);
	}
	
	@Transactional(readOnly = true)
	public List<TipPosete> findAll(Zaposleni zaposleni) {
		List<TipPosete> tipovi = new ArrayList<>();
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getKlinika().getId().equals(zaposleni.getKlinika().getId()) && tp.isAktivan())
				tipovi.add(tp);
		}
		return tipovi;
	}

	@Transactional(readOnly = true)
	public Set<String> sviTipovi(){
		
		Set<String> lista = new HashSet<>();
		for (TipPosete tp: this.tipPoseteRepository.findAll()) {
			if (tp.isPregled())
				lista.add(tp.getNaziv());
		}
		return lista;
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		TipPosete tipPosete = this.tipPoseteRepository.getOne(id);
		for (Poseta p: tipPosete.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				throw new MyRuntimeException();
		}
		for (Lekar l: this.lekarRepository.findAll()) {
			if (l.isAktivan() && l.getSpecijalizacija().getId().equals(id))
				throw new MyRuntimeException();
		}
		tipPosete.setAktivan(false);
		this.tipPoseteRepository.save(tipPosete);
	}
}
