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

import com.example.demo.dto.DijagnozaDTO;
import com.example.demo.dto.conversion.DijagnozaConversion;
import com.example.demo.service.DijagnozaService;

@RestController
@RequestMapping(value = "/dijagnoza")
public class DijagnozaController {
	

	@Autowired
	private DijagnozaService dijagnozaService;
	
	@Autowired
	private DijagnozaConversion dijagnozaConversion;
		
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/dobaviDijagnoze", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DijagnozaDTO> getLekovi(){
		return this.dijagnozaConversion.get(this.dijagnozaService.findAll());
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/dodavanje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody DijagnozaDTO dijagnozaDTO) {
		this.dijagnozaService.save(this.dijagnozaConversion.get(dijagnozaDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@DeleteMapping(value = "/brisanje/{dijagnozaId}")
	public ResponseEntity<?> delete(@PathVariable Integer dijagnozaId){
		this.dijagnozaService.delete(dijagnozaId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
