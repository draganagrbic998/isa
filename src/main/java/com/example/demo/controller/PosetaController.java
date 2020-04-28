package com.example.demo.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PregledAdmin;
import com.example.demo.dto.conversion.PosetaConversion;
import com.example.demo.dto.student1.KlinikaSlobodno;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.PosetaService;
import com.example.demo.service.UserService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@RestController
@RequestMapping(value="/poseta")
public class PosetaController {
	
	@Autowired
	private KlinikaService klinikaService;

	@Autowired
	private PosetaService posetaService;
	
	@Autowired
	private PosetaConversion posetaConversion;
	
	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;
				
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/zakazi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KlinikaSlobodno> zakazi(@PathVariable Integer id){

		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			this.posetaService.zakazi(id, pacijent.getKarton());

		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			this.emailService.sendMessage(new Message(pacijent.getEmail(), "Termin zakazan", "Zatrazeni termin je zakazan. "));
			return new ResponseEntity<>(this.klinikaService.getKlinikaSlobodno(id), HttpStatus.OK);

		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@DeleteMapping(value="/otkazi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> otkaziTermin(@PathVariable Integer id){	
		Poseta poseta = null;
		try {
			poseta = this.posetaService.otkazi(id);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
			String obavestenje = "Poseta zakazana za " + f.format(poseta.getDatum()) + " je otkazana od strane pacijenta. ";
			for (Lekar l: poseta.getLekari())
				this.emailService.sendMessage(new Message(l.getEmail(), "Otkazan termin", obavestenje));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}	
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/kreiraj", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody PregledAdmin pregled) {
		try {
			this.posetaService.save(this.posetaConversion.get(pregled));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	

}
