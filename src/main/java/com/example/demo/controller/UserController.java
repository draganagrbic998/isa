package com.example.demo.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KorisnikDTO;
import com.example.demo.dto.User;
import com.example.demo.model.Korisnik;
import com.example.demo.service.KorisnikService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private KorisnikService korisnikService;
	
	@PostMapping(value="/prijava", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> prijava(@RequestBody User user) {
		try {
			Korisnik k = this.userService.prijava(user);
			return new ResponseEntity<>(Hibernate.getClass(k).getSimpleName().toLowerCase(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> profil(){
		try {
			Korisnik k = this.userService.getSignedKorisnik();
			return new ResponseEntity<>(new KorisnikDTO(k), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> izmena(@RequestBody KorisnikDTO korisnikDTO){
		try {
			return new ResponseEntity<>(this.korisnikService.save(korisnikDTO), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value="/check/{uloga}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> checkUloga(@PathVariable String uloga){
		try {
			Korisnik k = this.userService.getSignedKorisnik();
			if (Hibernate.getClass(k).getSimpleName().toLowerCase().equals(uloga))
				return new ResponseEntity<>(HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
