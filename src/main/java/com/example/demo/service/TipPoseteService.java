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
	TipPoseteRepository tpRepository;
	
	@Autowired 
	UserService userservice;
	
	
	//da li je jedinstven u okviru klinike
	public boolean isUnique(TipPosete tipPosete) {
		for (TipPosete tp : tpRepository.findAll()) {
			if (tp.getNaziv().equals(tipPosete.getNaziv()) && tp.getKlinika()==tipPosete.getKlinika()) {
				return false;
			}
		}
		return true;
	}
	
	public void save(TipPosete tipPosete) {
		this.tpRepository.save(tipPosete);
	}
	
	public List<TipPosete> findForAdmin() {
		Admin admin = (Admin) userservice.getSignedKorisnik();
		List<TipPosete> types = new ArrayList<TipPosete>();
		for (TipPosete tp : this.tpRepository.findAll()) {
				if (admin.getKlinika()==tp.getKlinika()) {
					types.add(tp);
				}
		}
		return types;
	}
	
	
}
