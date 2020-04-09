package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.conversion.KartonConversion;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Pacijent;

@RestController
@RequestMapping(value="/pacijent")
public class PacijentController {
	
	//mozda cu ovaj kontroller razloziti na vise manjih, 
	//za sada sam razmisljala da je samo jedan kontroller
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KartonConversion kartonConversion;



	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> karton(){
		Korisnik korisnik = (Korisnik) this.session.getAttribute("korisnik");
		//ovo za hibernate proveru cemo jos videti jel ok
		Korisnik temp = (Korisnik) Hibernate.unproxy(korisnik);
		if (korisnik == null || !(temp instanceof Pacijent))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		Pacijent pacijent = (Pacijent) Hibernate.unproxy(korisnik);
		return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);
	}
}
