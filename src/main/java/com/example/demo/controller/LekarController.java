package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LekarDTO;
import com.example.demo.dto.ObavezaDTO;
import com.example.demo.dto.PacijentPretragaDTO;
import com.example.demo.dto.conversion.LekarConversion;
import com.example.demo.dto.student1.Bolest;
import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.model.Admin;
import com.example.demo.model.KrvnaGrupa;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.service.LekarService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/lekar")
public class LekarController {
	
	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private LekarConversion lekarConversion;
	
	@Autowired
	private UserService userService;
		
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LekarDTO>> pregled(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.lekarConversion.get(this.lekarService.findAll(admin)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody LekarDTO lekarDTO) {
		try {
			this.lekarService.save(this.lekarConversion.get(lekarDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping(value = "/brisanje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		try {
			this.lekarService.delete(id);
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
			return new ResponseEntity<>(new Bolest(this.lekarService.oceni(pacijent, param, posetaId)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LekarDTO> profil(){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.lekarConversion.get(lekar), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody LekarDTO lekarDTO){
		try {
			this.lekarService.save(this.lekarConversion.get(lekarDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/obaveze", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ObavezaDTO>> getObaveze(){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(lekar.getObaveze(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/pacijenti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PacijentPretragaDTO>> getPacijenteLekara(){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			List<Pacijent> pacijenti = this.lekarService.pacijentiLekara(lekar);
			List<PacijentPretragaDTO> pacijentiPretraga = new ArrayList<>();
			for (Pacijent p: pacijenti)
				pacijentiPretraga.add(new PacijentPretragaDTO(p, lekar));
			return new ResponseEntity<>(pacijentiPretraga, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value="/krvneGrupe", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KrvnaGrupa[]> getKrvneGrupe(){
		try {
			return new ResponseEntity<>(KrvnaGrupa.values(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
