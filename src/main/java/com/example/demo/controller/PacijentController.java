package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Bolest;
import com.example.demo.dto.Termin;
import com.example.demo.dto.conversion.KartonConversion;
import com.example.demo.model.Karton;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
import com.example.demo.service.PosetaService;
import com.example.demo.service.UserService;

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

	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> karton(){
		
		if (!(this.userService.authorized(Pacijent.class)))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
		return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/termini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> termini(){
		
		if (!(this.userService.authorized(Pacijent.class)))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();

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
		if (!(this.userService.authorized(Pacijent.class)))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();

		Karton karton = pacijent.getKarton();

		List<Bolest> bolesti = new ArrayList<Bolest>();
		for (Poseta p: karton.getPosete()) {
			if (p.getStanje().equals(StanjePosete.OBAVLJENO))
				bolesti.add(new Bolest(p));
		}
		return new ResponseEntity<>(bolesti, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/otkazi/termin/{posetaId}")
	public ResponseEntity<?> otkaziTermin(@PathVariable Integer posetaId){
		
		if (!(this.userService.authorized(Pacijent.class)))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);

		//mogla bih dodati da proverim da li je uneti id posete 
		//poseta koja se nalazi u listi pacijentovih poseta
		
		Poseta poseta = this.posetaService.getOne(posetaId);
		
		
		poseta.setKarton(null);
		poseta.setStanje(StanjePosete.SLOBODNO);
		this.posetaService.save(poseta);
		
		String obavestenje = "Poseta zakazana za " + poseta.getPopust() + " je otkazana od strane pacijenta. ";
		for (Lekar l: poseta.getLekari()) {
			this.emailService.sendMessage(new Message(l.getEmail(), "Otkazan termin", obavestenje));
		}
				

		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
}
