package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Izvestaj;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.repository.IzvestajRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.PosetaRepository;
import com.example.demo.repository.TerapijaRepository;
import com.example.demo.repository.ZahtevPosetaRepository;

@Component
@Transactional(readOnly = true)
public class PosetaService {

	@Autowired
	private PosetaRepository posetaRepository;
	
	@Autowired
	private ZahtevPosetaRepository zahtevRepository;

	@Autowired
	private IzvestajRepository izvestajRepository;
	
	@Autowired
	private TerapijaRepository terapijaRepository;
	
	@Autowired
	private LekarRepository lekarRepository;

	@Transactional(readOnly = false)
	public void save(Poseta poseta, Integer id) {
				
		if (!poseta.getTipPosete().isAktivan())
			throw new MyRuntimeException();
		
		if (!poseta.getSala().slobodan(poseta.pocetak(), poseta.kraj()))
			throw new MyRuntimeException();
		
		for (Lekar l: poseta.getLekari()) {
			if (!l.slobodan(poseta.pocetak(), poseta.kraj()) || 
					!l.slobodanZahtev(poseta.pocetak(), poseta.kraj(), id))
				throw new MyRuntimeException();
		}
		
		this.posetaRepository.save(poseta);
		
		for (Lekar l : poseta.getLekari()) {
			l.setPoslednjaIzmena(new Date());
			this.lekarRepository.save(l);
		}
		
		if (id != null)
			this.zahtevRepository.deleteById(id);
		
	}

	@Transactional(readOnly = false)
	public void delete(Integer id) {
		this.posetaRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Poseta getOne(Integer id) {
		return this.posetaRepository.getOne(id);
	}

	@Transactional(readOnly = false)
	public void zakazi(Integer id, Karton karton) {

		Poseta p = this.posetaRepository.getOne(id);
		if (!p.getStanje().equals(StanjePosete.SLOBODNO))
			throw new MyRuntimeException();
		if (!karton.slobodan(p.pocetak(), p.kraj()))
			throw new MyRuntimeException();

		p.setStanje(StanjePosete.ZAUZETO);
		p.setKarton(karton);
		this.posetaRepository.save(p);

	}

	@Transactional(readOnly = false)
	public Poseta otkazi(Integer id) {
		Poseta p = this.posetaRepository.getOne(id);
		this.posetaRepository.deleteById(id);
		return p;
	}

	@Transactional(readOnly = false)
	public void zapocni(Integer id, Lekar lekar) {
		
		Poseta p = this.posetaRepository.getOne(id);

		if (!p.getStanje().equals(StanjePosete.ZAUZETO))
			throw new MyRuntimeException();

		if (lekar.getZapocetaPoseta() != null)
			throw new MyRuntimeException();

		p.setStanje(StanjePosete.U_TOKU);
		this.posetaRepository.save(p);
		lekar.setZapocetaPoseta(p);
		this.lekarRepository.save(lekar);
		
	}

	@Transactional(readOnly = false)
	public void zavrsi(Izvestaj izvestaj, Lekar lekar) {
		
		lekar.setZapocetaPoseta(null);
		this.lekarRepository.save(lekar);
		this.terapijaRepository.save(izvestaj.getTerapija());
		this.izvestajRepository.save(izvestaj);

		izvestaj.getPoseta().setStanje(StanjePosete.OBAVLJENO);
		izvestaj.getPoseta().setIzvestaj(izvestaj);
		this.posetaRepository.save(izvestaj.getPoseta());

	}

	
}
