package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.conversion.KartonConversion;
import com.example.demo.dto.student1.Bolest;
import com.example.demo.dto.student1.KartonDTO;
import com.example.demo.dto.student1.Termin;
import com.example.demo.model.Karton;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.service.PosetaService;
import com.example.demo.service.UserService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@RestController
@RequestMapping(value="/pacijent")
public class PacijentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KartonConversion kartonConversion;
	
	@Autowired
	private PosetaService posetaService;
	
	@Autowired
	private EmailService emailService;

	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KartonDTO> karton(){
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
		return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);		
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/termini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Termin>> termini(){
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
		Karton karton = pacijent.getKarton();		
		List<Termin> termini = new ArrayList<>();
		for (Poseta p: karton.getPosete()) {
			if (p.getStanje().equals(StanjePosete.ZAUZETO))
				termini.add(new Termin(p));
			
		}
		return new ResponseEntity<>(termini, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/bolesti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bolest>> bolesti(){
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
		Karton karton = pacijent.getKarton();
		List<Bolest> bolesti = new ArrayList<>();
		for (Poseta p: karton.getPosete()) {
			if (p.getStanje().equals(StanjePosete.OBAVLJENO))
				bolesti.add(new Bolest(p));
		}
		return new ResponseEntity<>(bolesti, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@DeleteMapping(value="/otkazi/termin/{posetaId}")
	public ResponseEntity<HttpStatus> otkaziTermin(@PathVariable Integer posetaId){		
		Poseta poseta = this.posetaService.getOne(posetaId);
		poseta.setKarton(null);
		poseta.setStanje(StanjePosete.SLOBODNO);
		this.posetaService.save(poseta);
		String obavestenje = "Poseta zakazana za " + poseta.getDatum() + " je otkazana od strane pacijenta. ";
		for (Lekar l: poseta.getLekari()) {
			this.emailService.sendMessage(new Message(l.getEmail(), "Otkazan termin", obavestenje));
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
}
