package com.example.demo.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.IzvestajDTO;
import com.example.demo.dto.IzvestajUnosDTO;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Izvestaj;
import com.example.demo.model.Karton;
import com.example.demo.model.Lek;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.model.Terapija;
import com.example.demo.repository.DijagnozaRepository;
import com.example.demo.repository.IzvestajRepository;
import com.example.demo.repository.LekRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.PosetaRepository;
import com.example.demo.repository.TerapijaRepository;

@Component
@Transactional(readOnly = true)
public class PosetaService {

	@Autowired
	private PosetaRepository posetaRepository;

	@Autowired
	private LekarRepository lekarRepository;

	@Autowired
	private LekRepository lekRepository;

	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
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
		
		lekar.setZapocetaPoseta(null);
		this.lekarRepository.save(lekar);

		Terapija t = new Terapija(null, izvestaj, null);
		for (Integer id : izvestajUnosDTO.getLekovi())
			t.getLekovi().add(this.lekRepository.getOne(id));
		this.terapijaRepository.save(t);
		
		izvestaj.setTerapija(t);
		this.izvestajRepository.save(izvestaj);

		izvestaj.getPoseta().setStanje(StanjePosete.OBAVLJENO);
		izvestaj.getPoseta().setIzvestaj(izvestaj);
		this.posetaRepository.save(izvestaj.getPoseta());
		this.posetaRepository.save(izvestaj.getPoseta());

	}

	@Transactional(readOnly = false)
	public void izmeniIzvestaj(IzvestajDTO izvestajDTO) {
		Izvestaj izvestaj = this.izvestajRepository.getOne(izvestajDTO.getId());
		izvestaj.setOpis(izvestajDTO.getOpis());
		
		List<Dijagnoza> noveDijagnoze = new ArrayList<>();

		for (Integer id : izvestajDTO.getDijagnoze())
			noveDijagnoze.add(dijagnozaRepository.getOne(id));
		
		izvestaj.getDijagnoze().clear();
		
		for (Dijagnoza dijagnoza : noveDijagnoze)
			izvestaj.getDijagnoze().add(dijagnoza);
		
		List<Lek> noviLekovi = new ArrayList<>();

		for (Integer id : izvestajDTO.getLekovi())
			noviLekovi.add(lekRepository.getOne(id));
		
		izvestaj.getTerapija().getLekovi().clear();
		
		for (Lek lek : noviLekovi)
			izvestaj.getTerapija().getLekovi().add(lek);
		
		this.terapijaRepository.save(izvestaj.getTerapija());
		this.izvestajRepository.save(izvestaj);
	}
}
