package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.model.Ocena;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.OcenaRepository;
import com.example.demo.repository.PosetaRepository;

@Component
@Transactional(readOnly = true)
public class LekarService {
	
	
	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private PosetaRepository posetaRepository;
	
	@Transactional(readOnly = false)
	public void save(Lekar lekar) {
		this.lekarRepository.save(lekar);
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id) {
		Lekar l = this.lekarRepository.getOne(id);
		for (Poseta p: l.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				throw new RuntimeException();
		}
		l.setAktivan(false);
		this.lekarRepository.save(l);
	}
	
	
	@Transactional(readOnly = true)
	public List<Lekar> findAll(Admin admin) {
		List<Lekar> doctors = new ArrayList<>();
		for (Lekar l : this.lekarRepository.findAll()) {
			if (l.getKlinika().getId().equals(admin.getKlinika().getId()) && l.isAktivan())
				doctors.add(l);
		}
		return doctors;
	}
	
	@Transactional(readOnly = false)
	public Poseta oceni(Pacijent pacijent, OcenaParam param, Integer posetaId) {

		Lekar l = this.lekarRepository.getOne(param.getId());
		Ocena o = l.refreshOcena(pacijent, param.getOcena());
		this.ocenaRepository.save(o);
		return this.posetaRepository.getOne(posetaId);
		
	}

}


