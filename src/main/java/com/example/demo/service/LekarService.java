package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.repository.LekarRepository;

@Component
public class LekarService {
	
	
	@Autowired
	private LekarRepository lekarRepository;
	
	
	public void delete(Integer lekarId) {
		this.lekarRepository.deleteById(lekarId);
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
			if (l.getKlinika().getId().equals(admin.getKlinika().getId()))
				doctors.add(l);
		}
		return doctors;
	}

}


