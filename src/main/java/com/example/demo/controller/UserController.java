package com.example.demo.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping(value="/prijava", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> prijava(@RequestBody User user) {
		Korisnik k = this.userService.prijava(user);
		if (k == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(Hibernate.getClass(k).getSimpleName().toLowerCase(), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value="/ime/prezime")
	public ResponseEntity<String> imePrezime(){
		Korisnik k = this.userService.getSignedKorisnik();
		return new ResponseEntity<>(k.getIme() + " " + k.getPrezime(), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> profil(){
		Korisnik k = this.userService.getSignedKorisnik();
		return new ResponseEntity<>(new KorisnikDTO(k), HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value="/izmena")
	public ResponseEntity<String> izmena(@RequestBody KorisnikDTO korisnikDTO){
		Korisnik k = this.userService.getSignedKorisnik();
		k.setLozinka(korisnikDTO.getLozinka());
		k.setIme(korisnikDTO.getIme());
		k.setPrezime(korisnikDTO.getPrezime());
		k.setTelefon(korisnikDTO.getTelefon());
		k.setDrzava(korisnikDTO.getDrzava());
		k.setGrad(korisnikDTO.getGrad());
		k.setAdresa(korisnikDTO.getAdresa());
		this.korisnikService.save(k);
		return new ResponseEntity<>(Hibernate.getClass(k).getSimpleName().toLowerCase(), HttpStatus.OK);
	}
	
}
