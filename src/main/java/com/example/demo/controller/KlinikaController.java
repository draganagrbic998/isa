package com.example.demo.controller;

import java.util.Collection;
import java.util.List;

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

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.conversion.KlinikaConversion;
import com.example.demo.dto.student1.Bolest;
import com.example.demo.dto.student1.KlinikaPretraga;
import com.example.demo.dto.student1.KlinikaSlobodno;
import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.dto.student1.Pretraga;
import com.example.demo.model.Admin;
import com.example.demo.model.Pacijent;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/klinika")
public class KlinikaController {

	@Autowired
	private UserService userService;

	@Autowired
	private KlinikaService klinikaService;

	@Autowired
	private KlinikaConversion klinikaConversion;
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KlinikaDTO> getClinic(){
		try {
			Admin admin = (Admin) userService.getSignedKorisnik();
			return new ResponseEntity<>(this.klinikaConversion.get(admin.getKlinika()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/pregled",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaDTO>> review(){
		return new ResponseEntity<>(this.klinikaConversion.get(this.klinikaService.findAll()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody KlinikaDTO klinikaDTO) {
		try {
			this.klinikaService.save(this.klinikaConversion.get(klinikaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value = "/ocenjivanje/{posetaId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bolest> oceni(@PathVariable Integer posetaId, @RequestBody OcenaParam param){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(new Bolest(this.klinikaService.oceni(pacijent, param, posetaId)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaPretraga>> pretraga(){
		return new ResponseEntity<>(this.klinikaConversion.getPretraga(this.klinikaService.findAll()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<KlinikaPretraga>> pretragaParam(@RequestBody Pretraga param){
	
		return new ResponseEntity<>(this.klinikaService.pretraga(param), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/slobodno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaSlobodno>> slobodno(){
		return new ResponseEntity<>(this.klinikaService.slobodno(), HttpStatus.OK);
	}
	
}
