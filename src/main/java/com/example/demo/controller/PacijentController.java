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

import com.example.conversion.KartonConversion;
import com.example.conversion.PacijentConversion;
import com.example.demo.dto.model.KartonDTO;
import com.example.demo.dto.model.PacijentDTO;
import com.example.demo.dto.pretraga.BolestDTO;
import com.example.demo.dto.pretraga.TerminDTO;
import com.example.demo.model.Karton;
import com.example.demo.model.Pacijent;
import com.example.demo.service.PacijentService;
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
	private PacijentService pacijentService;
		

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
	public ResponseEntity<List<TerminDTO>> termini(){
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
	public ResponseEntity<List<BolestDTO>> bolesti(){
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

	
}
