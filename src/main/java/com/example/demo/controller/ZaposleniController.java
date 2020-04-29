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

import com.example.demo.dto.GodisnjiDTO;
import com.example.demo.dto.conversion.ZahtevOdmorConversion;
import com.example.demo.model.Zaposleni;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/zaposleni")
public class ZaposleniController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ZahtevOdmorConversion zahtevOdmorConversion;
	
	@PreAuthorize("hasAnyAuthority('Lekar','Sestra')")
	@GetMapping(value="/zahtevOdmor/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<GodisnjiDTO>> pregled(){
		try {
			Zaposleni zaposleni = (Zaposleni) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.zahtevOdmorConversion.get(zaposleni.getOdmorZahtevi()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
