package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.User;
import com.example.demo.model.Korisnik;
import com.example.demo.service.KorisnikService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KorisnikService korisnikService;
		
	@PostMapping(value="/prijava", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> prijava(@RequestBody User user) {
		
		Korisnik k = this.korisnikService.prijava(user);
		if (k == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		this.session.setAttribute("korisnik", k);
		return new ResponseEntity<>(Hibernate.getClass(k).getSimpleName().toLowerCase(), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/ime/prezime")
	public ResponseEntity<?> imePrezime(){
		Korisnik k = (Korisnik) this.session.getAttribute("korisnik");
		if (k == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(k.getIme() + " " + k.getPrezime(), HttpStatus.OK);
	}
	
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> profil(){
		Korisnik k = (Korisnik) this.session.getAttribute("korisnik");
		if (k == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(new KorisnikDTO(k), HttpStatus.OK);
	}
	
	@PostMapping(value="/izmena")
	public ResponseEntity<?> izmena(@RequestBody KorisnikDTO korisnikDTO){
		Korisnik k = (Korisnik) this.session.getAttribute("korisnik");
		if (k == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		Korisnik korisnik = this.korisnikService.getOne(korisnikDTO.getId());
		korisnik.setLozinka(korisnikDTO.getLozinka());
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		korisnik.setTelefon(korisnikDTO.getTelefon());
		korisnik.setDrzava(korisnikDTO.getDrzava());
		korisnik.setGrad(korisnikDTO.getGrad());
		korisnik.setAdresa(korisnikDTO.getAdresa());
		this.korisnikService.save(korisnik);
		
		this.session.setAttribute("korisnik", korisnik);
		return new ResponseEntity<>(Hibernate.getClass(korisnik).getSimpleName().toLowerCase(), HttpStatus.OK);
	}
	
}
