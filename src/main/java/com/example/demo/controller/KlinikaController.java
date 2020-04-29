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

import com.example.demo.conversion.all.KlinikaConversion;
import com.example.demo.dto.model.KlinikaDTO;
import com.example.demo.dto.pretraga.BolestDTO;
import com.example.demo.dto.pretraga.KlinikaPretragaDTO;
import com.example.demo.dto.pretraga.KlinikaSlobodnoDTO;
import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.dto.unos.PretragaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Pacijent;
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
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody KlinikaDTO klinikaDTO){
		try {
			this.klinikaService.save(this.klinikaConversion.get(klinikaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
	public ResponseEntity<BolestDTO> oceni(@PathVariable Integer posetaId, @RequestBody OcenaParamDTO param){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(new BolestDTO(this.klinikaService.oceni(pacijent, param, posetaId)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaPretragaDTO>> pretraga(){
		try {
			return new ResponseEntity<>(this.klinikaConversion.getPretraga(this.klinikaService.findAll()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<KlinikaPretragaDTO>> pretragaParam(@RequestBody PretragaDTO param){
		try {
			return new ResponseEntity<>(this.klinikaService.pretraga(param), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/slobodno", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KlinikaSlobodnoDTO>> slobodno(){
		try {
			return new ResponseEntity<>(this.klinikaService.slobodno(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
