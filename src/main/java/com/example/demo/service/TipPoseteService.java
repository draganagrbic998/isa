package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.model.TipPosete;
import com.example.demo.repository.TipPoseteRepository;

@Component
@Transactional(readOnly = true)
public class TipPoseteService {

	@Autowired
	private TipPoseteRepository tipPoseteRepository;
		
	@Transactional(readOnly = false)
	public void save(TipPosete tipPosete) throws Exception {
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getNaziv().equals(tipPosete.getNaziv()) && tp.getKlinika().getId().equals(tipPosete.getKlinika().getId()))
				throw new RuntimeException();
		}
		this.tipPoseteRepository.save(tipPosete);
	}
	
	@Transactional(readOnly = true)
	public List<TipPosete> findForAdmin(Admin admin) {
		List<TipPosete> types = new ArrayList<>();
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getKlinika().getId().equals(admin.getKlinika().getId()) && tp.isAktivan())
				types.add(tp);
		}
		return types;
	}

	@Transactional(readOnly = true)
	public Set<String> sviTipovi(){
		
		Set<String> lista = new HashSet<>();
		for (TipPosete tp: this.tipPoseteRepository.findAll())
			lista.add(tp.getNaziv());
		return lista;
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Integer id, List<Lekar> lekari) {
		TipPosete tipPosete = this.tipPoseteRepository.getOne(id);
		for (Poseta p: tipPosete.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO))
				throw new RuntimeException();
		}
		tipPosete.setAktivan(false);
		this.tipPoseteRepository.save(tipPosete);
	}
}
