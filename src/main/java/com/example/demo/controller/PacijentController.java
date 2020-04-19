package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KartonDTO;
import com.example.demo.dto.conversion.KartonConversion;
import com.example.demo.dto.student1.Bolest;
import com.example.demo.dto.student1.Termin;
import com.example.demo.model.Karton;
import com.example.demo.model.Pacijent;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/pacijent")
public class PacijentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KartonConversion kartonConversion;
		

	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KartonDTO> karton(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);		
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/termini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Termin>> termini(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();		
			return new ResponseEntity<>(karton.getTermini(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/bolesti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bolest>> bolesti(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();
			return new ResponseEntity<>(karton.getBolesti(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
