package com.example.demo.controller;

import java.util.List;
import java.util.Set;

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

import com.example.demo.dto.TipPoseteDTO;
import com.example.demo.dto.conversion.TipPoseteConversion;
import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.service.LekarService;
import com.example.demo.service.TipPoseteService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/tipPosete")
public class TipPoseteController {

	@Autowired
	private TipPoseteService tipPoseteService;
	
	@Autowired
	private TipPoseteConversion tipPoseteConversion;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LekarService lekarService;
		
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody TipPoseteDTO tipPoseteDTO) {
		try {
			this.tipPoseteService.save(this.tipPoseteConversion.get(tipPoseteDTO));
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
			Admin admin = (Admin) this.userService.getSignedKorisnik();			
			this.tipPoseteService.delete(id, this.lekarService.findAll(admin));
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipPoseteDTO>> pregled() {
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.tipPoseteConversion.get(this.tipPoseteService.findAll(admin)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@GetMapping(value = "/lekar/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipPoseteDTO>> pregledLekar() {
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.tipPoseteConversion.get(this.tipPoseteService.findAll(lekar)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/nazivi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<String>> nazivi(){
		
		try {
			return new ResponseEntity<>(this.tipPoseteService.sviTipovi(), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
		
}
