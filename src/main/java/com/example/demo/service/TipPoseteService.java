package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.model.TipPosete;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class TipPoseteService {

	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private LekarService lekarService;
	
	public void save(TipPosete tipPosete) throws Exception {
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getNaziv().equals(tipPosete.getNaziv()) && tp.getKlinika().getId().equals(tipPosete.getKlinika().getId()))
				throw new RuntimeException();
		}
		this.tipPoseteRepository.save(tipPosete);
	}
	
	public List<TipPosete> findForAdmin(Admin admin) {
		List<TipPosete> types = new ArrayList<>();
		for (TipPosete tp : this.tipPoseteRepository.findAll()) {
			if (tp.getKlinika().getId().equals(admin.getKlinika().getId()) && tp.isAktivan())
				types.add(tp);
		}
		return types;
	}

	public Set<String> sviTipovi(){
		
		Set<String> lista = new HashSet<>();
		for (TipPosete tp: this.tipPoseteRepository.findAll())
			lista.add(tp.getNaziv());
		return lista;
		
	}
	
	//logicko brisanje tipa pregleda
	public void delete(Integer id, List<Lekar> lekari) {
		TipPosete tp = this.tipPoseteRepository.getOne(id);
		for (Lekar l : lekari) {
			for (Poseta p : l.getPosete()) {
				if ((p.getStanje().equals(StanjePosete.U_TOKU) || p.getStanje().equals(StanjePosete.ZAUZETO)) && p.getTipPosete().equals(tp)) {
					throw new RuntimeException();
				}
			}
		}
		tp.setAktivan(false);
		this.tipPoseteRepository.save(tp);
	}
}
