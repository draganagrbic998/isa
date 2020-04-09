package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Bolest;
import com.example.demo.dto.Termin;
import com.example.demo.dto.conversion.KartonConversion;
import com.example.demo.model.Karton;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;

@RestController
@RequestMapping(value="/pacijent")
public class PacijentController {
		
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KartonConversion kartonConversion;

	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> karton(){
		Korisnik k = (Korisnik) this.session.getAttribute("korisnik");
		Korisnik korisnik = (Korisnik) Hibernate.unproxy(k);
		if (k == null || !(korisnik instanceof Pacijent))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Pacijent pacijent = (Pacijent) korisnik;
		return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);
	}
	
	@GetMapping(value="/termini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> termini(){
		Korisnik k = (Korisnik) this.session.getAttribute("korisnik");
		Korisnik korisnik = (Korisnik) Hibernate.unproxy(k);
		if (k == null || !(korisnik instanceof Pacijent))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Pacijent pacijent = (Pacijent) korisnik;

		Karton karton = pacijent.getKarton();
		//kada budem otkazivala posete, moram da pazim da referencu POseta izbacim iz liste poseta kartona
		//to cu proveravati kad budemo uradili kreiranje poseta
		
		List<Termin> termini = new ArrayList<Termin>();

		
		
		for (Poseta p: karton.getPosete()) {
			if (p.getStanje().equals(StanjePosete.ZAUZETO))
				termini.add(new Termin(p));
			
		}
		return new ResponseEntity<>(termini, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/bolesti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> bolesti(){
		Korisnik k = (Korisnik) this.session.getAttribute("korisnik");
		Korisnik korisnik = (Korisnik) Hibernate.unproxy(k);
		if (k == null || !(korisnik instanceof Pacijent))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Pacijent pacijent = (Pacijent) korisnik;
		Karton karton = pacijent.getKarton();

		List<Bolest> bolesti = new ArrayList<Bolest>();
		for (Poseta p: karton.getPosete()) {
			if (p.getStanje().equals(StanjePosete.OBAVLJENO))
				bolesti.add(new Bolest(p));
		}
		return new ResponseEntity<>(bolesti, HttpStatus.OK);
		
	}
	
}
