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

import com.example.demo.dto.LekarDTO;
import com.example.demo.dto.conversion.LekarConversion;
import com.example.demo.model.Admin;
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
	@GetMapping(value = "/dobaviLekare", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LekarDTO> getDoctors(){
		Admin admin = (Admin) this.userService.getSignedKorisnik();
		return this.lekarConversion.get(this.lekarService.findAllOneClinic(admin));
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody LekarDTO lekarDTO) {
		this.lekarService.save(this.lekarConversion.get(lekarDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping(value = "/brisanje/{lekarId}")
	public ResponseEntity<?> delete(@PathVariable Integer lekarId){
		this.lekarService.delete(lekarId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
