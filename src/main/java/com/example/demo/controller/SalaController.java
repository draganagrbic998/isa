package com.example.demo.controller;

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

import com.example.demo.conversion.total.SalaConversion;
import com.example.demo.dto.model.SalaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.service.SalaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/sala")
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SalaConversion salaConversion;
		
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody SalaDTO salaDTO) {
		try {
			this.salaService.save(this.salaConversion.get(salaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> getS(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.salaConversion.get(this.salaService.findAll(admin)), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping(value = "/brisanje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id){
		try {
			this.salaService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);			
		}
	}
		
}
