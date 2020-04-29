package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.GodisnjiDTO;
import com.example.demo.model.Sestra;
import com.example.demo.model.ZahtevOdmor;
import com.example.demo.model.Zaposleni;
import com.example.demo.repository.SestraRepository;

@Component
@Transactional(readOnly = true)
public class SestraService {
	
	@Autowired
	private SestraRepository sestraRepository;
	
	@Transactional(readOnly = false)
	public void save(Sestra sestra) {
		this.sestraRepository.save(sestra);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		Sestra sestra = this.sestraRepository.getOne(id);
		sestra.setAktivan(false);
		this.sestraRepository.save(sestra);
	}
	
	@Transactional(readOnly = true)
	public List<Sestra> findAll(Zaposleni admin) {
		List<Sestra> sestre = new ArrayList<>();
		for (Sestra s : this.sestraRepository.findAll()) {
			if (s.getKlinika().getId().equals(admin.getKlinika().getId()) && s.isAktivan())
				sestre.add(s);
		}
		return sestre;
	}

	@Transactional(readOnly = true)
	public List<GodisnjiDTO> getGodisnji(Sestra sestra) {
		List<GodisnjiDTO> godisnji = new ArrayList<GodisnjiDTO>();

		for (ZahtevOdmor z : sestra.getOdmorZahtevi()) {
			if (z.getOdobren()) {
				godisnji.add(new GodisnjiDTO(z.getId(), z.getPocetak(), z.getKraj(), z.getTrajanje()));
			}
		}

		return godisnji;
	}

}


