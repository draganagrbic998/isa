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
	
	//Ovo Hibernate. pa nest trebace vam ako koristiti polimorfizam (bar meni tada trebalo)
	
	@PostMapping(value="/prijava", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> prijava(@RequestBody User user) {
		
		Korisnik korisnik = this.korisnikService.prijava(user);
		if (korisnik == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		this.session.setAttribute("korisnik", korisnik);
		return new ResponseEntity<>(Hibernate.getClass(korisnik).getSimpleName().toLowerCase(), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/ime/prezime")
	public ResponseEntity<?> imePrezime(){
		Korisnik korisnik = (Korisnik) this.session.getAttribute("korisnik");
		if (korisnik == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(korisnik.getIme() + " " + korisnik.getPrezime(), HttpStatus.OK);
	}
	
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> profil(){
		Korisnik korisnik = (Korisnik) this.session.getAttribute("korisnik");
		if (korisnik == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//ne treba KorisnikConversion za samo ovo
		return new ResponseEntity<>(new KorisnikDTO(korisnik), HttpStatus.OK);
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
		this.session.setAttribute("korisnik", korisnik);

		this.korisnikService.save(korisnik);
		
		//sto mora ovo osvezavanje sesije?

		//ovo sa hibernate proxy mozda da ne koristite

		return new ResponseEntity<>(Hibernate.getClass(korisnik).getSimpleName().toLowerCase(), HttpStatus.OK);
	}
	
}
