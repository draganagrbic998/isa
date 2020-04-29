package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.IzvestajUnosDTO;
import com.example.demo.model.Izvestaj;
import com.example.demo.model.Karton;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.model.Terapija;
import com.example.demo.repository.IzvestajRepository;
import com.example.demo.repository.LekRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.PacijentRepository;
import com.example.demo.repository.PosetaRepository;
import com.example.demo.repository.TerapijaRepository;

@Component
@Transactional(readOnly = true)
public class PosetaService {

	@Autowired
	private PosetaRepository posetaRepository;

	@Autowired
	private PacijentRepository pacijentRepository;

	@Autowired
	private LekarRepository lekarRepository;

	@Autowired
	LekRepository lekRepository;

	@Autowired
	IzvestajRepository izvestajRepository;
	
	@Autowired
	TerapijaRepository terapijaRepository;

	@Transactional(readOnly = false)
	public void save(Poseta poseta) {
		this.posetaRepository.save(poseta);
		for (Lekar l : poseta.getLekari()) {
			l.setPoslednjaIzmena(new Date());
			this.lekarRepository.save(l);
		}

	}

	@Transactional(readOnly = false)
	public void deleteById(Integer posetaId) {
		this.posetaRepository.deleteById(posetaId);
	}

	@Transactional(readOnly = false)
	public void zakazi(Integer posetaId, Karton karton) {

		Poseta p = this.posetaRepository.getOne(posetaId);
		if (!p.getStanje().equals(StanjePosete.SLOBODNO))
			throw new RuntimeException();
		p.setStanje(StanjePosete.ZAUZETO);
		p.setKarton(karton);
		this.posetaRepository.save(p);

	}

	@Transactional(readOnly = false)
	public Poseta otkazi(Integer id) {
		Poseta poseta = this.posetaRepository.getOne(id);
		this.posetaRepository.deleteById(id);
		return poseta;
	}

	// lekar u toku moze imati samo 1 posetu! to je pretpostavka
	@Transactional(readOnly = false)
	public Poseta nadjiUToku(Lekar lekar) {
		for (Poseta p : this.posetaRepository.findAll()) {
			if (p.getLekari().contains(this.lekarRepository.getOne(lekar.getId()))
					&& p.getStanje().equals(StanjePosete.U_TOKU)) {
				return p;
			}
		}
		return null;
	}

	// vraca listu izvestaja za poseta pacijenta
	@Transactional(readOnly = false)
	public List<Izvestaj> nadjiIzvestaje(Integer id) {
		List<Izvestaj> izvestaji = new ArrayList<Izvestaj>();
		Pacijent pacijent = this.pacijentRepository.getOne(id);
		for (Poseta poseta : pacijent.getKarton().getPosete()) {
			if (!izvestaji.contains(poseta.getIzvestaj()) && poseta.getStanje().equals(StanjePosete.OBAVLJENO)) {
				izvestaji.add(poseta.getIzvestaj());
			}
		}
		return izvestaji;
	}

	// nalazi zakazanu posetu lekara
	@Transactional(readOnly = false)
	public Poseta nadjiZakazanu(Lekar lekar) {
		Date danas = new Date();
		for (Poseta p : lekar.getPosete()) {
			if (p.getDatum().after(danas) && p.getStanje().equals(StanjePosete.ZAUZETO)) {
				return p;
			}
		}
		return null;
	}

	@Transactional(readOnly = false)
	public void zapocni(Integer id, Lekar lekar) {
		Poseta p = this.posetaRepository.getOne(id);

		if (!p.getStanje().equals(StanjePosete.ZAUZETO))
			throw new RuntimeException();

		if (lekar.getZapocetaPoseta() != null)
			throw new RuntimeException();

		p.setStanje(StanjePosete.U_TOKU);
		this.posetaRepository.save(p);

		lekar.setZapocetaPoseta(p);
		this.lekarRepository.save(lekar);
	}

	@Transactional(readOnly = false)
	public void zavrsi(Izvestaj izvestaj, IzvestajUnosDTO izvestajUnosDTO, Lekar lekar) {
		izvestaj.getPoseta().setStanje(StanjePosete.OBAVLJENO);
		posetaRepository.save(izvestaj.getPoseta());
		
		lekar.setZapocetaPoseta(null);
		lekarRepository.save(lekar);
		
		izvestajRepository.save(izvestaj);
		
		Terapija t = new Terapija(null, izvestaj, null);

		for (Integer terapijaId : izvestajUnosDTO.getLekovi())
			t.getLekovi().add(lekRepository.getOne(terapijaId));

		terapijaRepository.save(t);
		
		izvestaj.setTerapija(t);
		izvestajRepository.save(izvestaj);
		
		izvestaj.getPoseta().setIzvestaj(izvestaj);
		posetaRepository.save(izvestaj.getPoseta());
	}
}
