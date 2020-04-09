package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		Korisnik korisnik = this.korisnikService.prijava(user);
		if (korisnik == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		this.session.setAttribute("korisnik", korisnik);
		return new ResponseEntity<>(korisnik.getClass().getSimpleName().toLowerCase(), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/ime/prezime")
	public ResponseEntity<?> imePrezime(){
		Korisnik korisnik = (Korisnik) this.session.getAttribute("korisnik");
		if (korisnik == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(korisnik.getIme() + " " + korisnik.getPrezime(), HttpStatus.OK);
	}
	
	//ovde ide jos profil i izmena profila
	
}
