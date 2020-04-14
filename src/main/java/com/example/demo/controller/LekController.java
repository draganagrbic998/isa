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

import com.example.demo.dto.LekDTO;
import com.example.demo.dto.conversion.LekConversion;
import com.example.demo.service.LekService;

@RestController
@RequestMapping(value = "/lek")
public class LekController {
	

	@Autowired
	private LekService lekService;
	
	@Autowired
	private LekConversion lekConversion;
		
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/dobaviLekove", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LekDTO> getLekovi(){
		return this.lekConversion.get(this.lekService.findAll());
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/dodavanje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody LekDTO lekDTO) {
		this.lekService.save(this.lekConversion.get(lekDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@DeleteMapping(value = "/brisanje/{lekId}")
	public ResponseEntity<?> delete(@PathVariable Integer lekId){
		try {
			this.lekService.delete(lekId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}
