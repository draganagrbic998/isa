package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.OcenaRepository;
import com.example.demo.repository.PacijentRepository;

@Component
@Transactional(readOnly = true)
public class LekarService {

	@Autowired
	private LekarRepository lekarRepository;

	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private PacijentRepository pacijentRepository;

	@Transactional(readOnly = false)
	public void save(Lekar lekar) {
		this.lekarRepository.save(lekar);
	}
	
	@Transactional(readOnly = false)
	public Lekar getOne(Integer id) {
		return this.lekarRepository.getOne(id);
	}

	@Transactional(readOnly = true)
	public List<Lekar> getOnes(List<Integer> ids) {
		List<Lekar> lekari = new ArrayList<>();
		for (Integer id: ids)
			lekari.add(this.lekarRepository.getOne(id));
		return lekari;
	}
	
	@Transactional(readOnly = true)
	public List<Lekar> findAll(Admin admin) {
		List<Lekar> lekari = new ArrayList<>();
		for (Lekar l : this.lekarRepository.findByKlinikaId(admin.getKlinika().getId())) {
			if (l.isAktivan())
				lekari.add(l);
		}
		return lekari;
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		Lekar l = this.lekarRepository.getOne(id);
		if (!l.mozeBrisanje())
			throw new MyRuntimeException();
		l.setAktivan(false);
		this.lekarRepository.save(l);
	}

	@Transactional(readOnly = false)
	public Lekar oceni(Pacijent pacijent, OcenaParamDTO param) {

		Lekar l = this.lekarRepository.getOne(param.getId());
		Ocena o = l.refreshOcena(pacijent, param.getOcena());
		this.ocenaRepository.save(o);
		return l;

	}	
	
	@Transactional(readOnly = true)
	public List<Pacijent> pacijentiLekara(Lekar lekar) {
		List<Pacijent> pacijenti = new ArrayList<>();
		for (Pacijent pacijent : this.pacijentRepository.findAll()) {
			if (pacijent.posetioLekara(lekar))
				pacijenti.add(pacijent);
		}
		return pacijenti;
	}

}
