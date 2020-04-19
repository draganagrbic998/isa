package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.student1.KlinikaPretraga;
import com.example.demo.dto.student1.KlinikaSlobodno;
import com.example.demo.dto.student1.LekarSatnica;
import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.dto.student1.Pretraga;
import com.example.demo.model.Klinika;
import com.example.demo.model.Lekar;
import com.example.demo.model.Ocena;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.Sala;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.OcenaRepository;
import com.example.demo.repository.PosetaRepository;

@Component
public class KlinikaService {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private OcenaRepository ocenaRepository;
	
	@Autowired
	private PosetaRepository posetaRepository;
	
	@Autowired
	private LekarRepository lekarRepository;

	public void save(Klinika klinika) {
		this.klinikaRepository.save(klinika);
	}
	
	public List<Klinika> findAll(){
		return this.klinikaRepository.findAll();
	}
	
	public Poseta oceni(Pacijent pacijent, OcenaParam param, Integer posetaId) {
		
		Klinika k = this.klinikaRepository.getOne(param.getId());
		Ocena o = k.refreshOcena(pacijent, param.getOcena());
		this.ocenaRepository.save(o);
		this.klinikaRepository.save(k);
		return this.posetaRepository.getOne(posetaId);
		
	}
	
	public List<Poseta> getPosete(Klinika klinika){
		
		List<Poseta> lista = new ArrayList<>();
		for (Poseta p: this.posetaRepository.findAll()) {
			for (Sala s: klinika.getSale()) {
				if (s.getId().equals(p.getSala().getId()))
					lista.add(p);
			}
		}
		return lista;
		
	}

	public List<KlinikaSlobodno> slobodno() {
		
		List<KlinikaSlobodno> lista = new ArrayList<>();
		for (Klinika k: this.klinikaRepository.findAll())
			lista.add(new KlinikaSlobodno(k, this.getPosete(k)));
		return lista;

	}

	

	public KlinikaSlobodno getKlinikaSlobodno(Integer posetaId) {

		Poseta p = this.posetaRepository.getOne(posetaId);
		Klinika k = p.getSala().getKlinika();
		return new KlinikaSlobodno(k, this.getPosete(k));

	}

	public Collection<KlinikaPretraga> pretraga(Pretraga param) {

		Map<Integer, KlinikaPretraga> mapa = new HashMap<>();
		for (Lekar l: this.lekarRepository.findAll()) {
			if (l.getSpecijalizacija().getNaziv().equalsIgnoreCase(param.getTipPregleda()) && l.getSpecijalizacija().getPregled()) {
				List<Date> satnica = l.getSatnica(param.getDatumPregleda());

				if (satnica.size() > 0) {
					LekarSatnica ls = new LekarSatnica(l, satnica);
					KlinikaPretraga kp;
					if (mapa.containsKey(l.getKlinika().getId())) {
						kp = mapa.get(l.getKlinika().getId());
					}
					else {
						kp = new KlinikaPretraga(l.getKlinika(), l.getSpecijalizacija().getCena(), l.getSpecijalizacija().getSati() + l.getSpecijalizacija().getMinute() / 60.0);
						mapa.put(l.getKlinika().getId(), kp);
					}
					kp.dodaj(ls);
				}
			}
			
		}
		return mapa.values();
		
	}

	
}
