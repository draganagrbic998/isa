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

import com.example.demo.conversion.partial.KartonConversion;
import com.example.demo.conversion.total.LekarConversion;
import com.example.demo.dto.model.IzvestajDTO;
import com.example.demo.dto.model.KartonDTO;
import com.example.demo.dto.model.LekarDTO;
import com.example.demo.dto.pretraga.BolestDTO;
import com.example.demo.dto.pretraga.ObavezaDTO;
import com.example.demo.dto.pretraga.PacijentPretragaDTO;
import com.example.demo.dto.unos.OcenaParamDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.KrvnaGrupa;
import com.example.demo.service.KartonService;
import com.example.demo.service.LekarService;
import com.example.demo.service.PosetaService;
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
	
	@Autowired
	private KartonService kartonService;
	
	@Autowired
	private KartonConversion kartonConversion;

	
	@Autowired
	private PosetaService posetaService;
		
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
	public ResponseEntity<BolestDTO> oceni(@PathVariable Integer posetaId, @RequestBody OcenaParamDTO param){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(new BolestDTO(this.lekarService.oceni(pacijent, param, posetaId)), HttpStatus.OK);
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
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value="/izmenaKartona", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody KartonDTO kartonDTO){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			
			if (lekar.getZapocetaPoseta() == null)
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			
			if (!lekar.getZapocetaPoseta().getKarton().getPacijent().getId().equals(kartonDTO.getPacijent()))
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			
			this.kartonService.save(this.kartonConversion.get(kartonDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value = "/izmenaIzvestaja", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> zavrsi(@RequestBody IzvestajDTO izvestajDTO) {
		try {
			this.posetaService.izmeniIzvestaj(izvestajDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}
