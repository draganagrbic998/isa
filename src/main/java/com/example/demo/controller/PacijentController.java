package com.example.demo.controller;

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

import com.example.demo.dto.IzvestajDTO;
import com.example.demo.dto.KartonDTO;
import com.example.demo.dto.PacijentDTO;
import com.example.demo.dto.conversion.KartonConversion;
import com.example.demo.dto.conversion.PacijentConversion;
import com.example.demo.dto.student1.Bolest;
import com.example.demo.dto.student1.Termin;
import com.example.demo.model.Karton;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.service.KartonService;
import com.example.demo.service.PacijentService;
import com.example.demo.service.PosetaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/pacijent")
public class PacijentController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KartonConversion kartonConversion;
		
	@Autowired
	private PacijentConversion pacijentConversion;
		
	@Autowired
	private KartonService kartonService;
	
	@Autowired
	private PacijentService pacijentService;
	
	
	@Autowired
	private PosetaService posetaService;
	

	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/karton", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KartonDTO> karton(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.kartonConversion.get(pacijent.getKarton()), HttpStatus.OK);		
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/termini", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Termin>> termini(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();		
			return new ResponseEntity<>(karton.getTermini(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/bolesti", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Bolest>> bolesti(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			Karton karton = pacijent.getKarton();
			return new ResponseEntity<>(karton.getBolesti(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/profil", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacijentDTO> profil(){
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.pacijentConversion.get(pacijent), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/izmena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> izmena(@RequestBody PacijentDTO pacijentDTO){
		try {
			this.pacijentService.save(this.pacijentConversion.get(pacijentDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/aktiviraj/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> aktiviraj(@PathVariable Integer id){
		try {
			boolean retval = this.pacijentService.aktiviraj(id);
			if (retval)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value="/lekar/izmenaKartona", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
