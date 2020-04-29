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
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.model.Terapija;
import com.example.demo.repository.DijagnozaRepository;
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
	DijagnozaRepository dijagnozaRepository;
	
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
	public List<Izvestaj> nadjiIzvestaje(Integer id, Lekar lekar) {
		List<Izvestaj> izvestaji = new ArrayList<Izvestaj>();
		Pacijent pacijent = this.pacijentRepository.getOne(id);
		for (Poseta poseta : pacijent.getKarton().getPosete()) {
			if (lekar.getPosete().contains(poseta)) {
				if (!izvestaji.contains(poseta.getIzvestaj()) && poseta.getStanje().equals(StanjePosete.OBAVLJENO)) {
					izvestaji.add(poseta.getIzvestaj());
				}
			}
		}
		return izvestaji;
	}

	// nalazi zakazanu posetu lekara
	@Transactional(readOnly = false)
	public Poseta nadjiZakazanu(Lekar lekar) {
		Date danas = new Date();
		
		for (Poseta p : lekar.getPosete()) {
			Date datumPosete = p.getDatum();
			long diff = datumPosete.getTime() - danas.getTime();
	        long diffMinutes = diff / (60 * 1000) % 60;
	        int diffInDays = (int) ((datumPosete.getTime() - danas.getTime()) / (1000 * 60 * 60 * 24));
	        if (diffInDays==0 && (-15 <= diffMinutes) && (diffMinutes <= 5) && p.getStanje().equals(StanjePosete.ZAUZETO)){
			//if (p.getDatum().equals(danas) && p.getStanje().equals(StanjePosete.ZAUZETO)) {
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

	@Transactional(readOnly = false)
	public void izmeniIzvestaj(IzvestajDTO izvestajDTO) {
		Izvestaj izvestaj = izvestajRepository.getOne(izvestajDTO.getId());
		izvestaj.setOpis(izvestajDTO.getOpis());
		
		List<Dijagnoza> noveDijagnoze = new ArrayList<Dijagnoza>();

		for (Integer dijagnozaId : izvestajDTO.getDijagnoze())
			noveDijagnoze.add(dijagnozaRepository.getOne(dijagnozaId));
		
		izvestaj.getDijagnoze().clear();
		
		for (Dijagnoza dijagnoza : noveDijagnoze)
			izvestaj.getDijagnoze().add(dijagnoza);
		
		List<Lek> noviLekovi = new ArrayList<Lek>();

		for (Integer lekId : izvestajDTO.getLekovi())
			noviLekovi.add(lekRepository.getOne(lekId));
		
		izvestaj.getTerapija().getLekovi().clear();
		
		for (Lek lek : noviLekovi)
			izvestaj.getTerapija().getLekovi().add(lek);
		
		terapijaRepository.save(izvestaj.getTerapija());
		izvestajRepository.save(izvestaj);
	}
}
