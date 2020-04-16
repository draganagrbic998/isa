package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.model.TipPosete;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class TipPoseteService {

	@Autowired
	private TipPoseteRepository tipPoseteRepository;
		
	//da li je jedinstven u okviru klinike
	public boolean isUnique(TipPosete tipPosete) {
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getNaziv().equals(tipPosete.getNaziv()) && tp.getKlinika().getId().equals(tipPosete.getKlinika().getId()))
				return false;
		}
		return true;
	}
	
	public void save(TipPosete tipPosete) {
		this.tipPoseteRepository.save(tipPosete);
	}
	
	public List<TipPosete> findForAdmin(Admin admin) {
		List<TipPosete> types = new ArrayList<TipPosete>();
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getKlinika().getId().equals(admin.getKlinika().getId()))
				types.add(tp);
		}
		return types;
	}
	
	
}
