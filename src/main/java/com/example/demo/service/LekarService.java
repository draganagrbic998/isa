package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.repository.LekarRepository;

@Component
public class LekarService {
	
	
	@Autowired
	private LekarRepository lekarRepository;
	
	
	public boolean delete(Integer id) {
		Lekar l = this.lekarRepository.getOne(id);
		for (Poseta p: l.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				return false;
		}
		l.setAktivan(false);
		this.lekarRepository.save(l);
		return true;
	}
	
	public void save(Lekar lekar) {
		this.lekarRepository.save(lekar);
	}
	
	public List<Lekar> findAll(){
		return this.lekarRepository.findAll();
	}
	
	public List<Lekar> findAllOneClinic(Admin admin) {
		List<Lekar> doctors = new ArrayList<>();
		for (Lekar l : this.lekarRepository.findAll()) {
			if (l.getKlinika().getId().equals(admin.getKlinika().getId()) && l.getAktivan())
				doctors.add(l);
		}
		return doctors;
	}

}


