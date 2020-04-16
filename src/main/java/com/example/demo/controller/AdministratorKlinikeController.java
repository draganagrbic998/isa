package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminDTO;
import com.example.demo.dto.conversion.AdministratorKlinikeConversion;
import com.example.demo.service.AdministratorKlinikeService;

@RestController
@RequestMapping(value = "/adminKlinike")
public class AdministratorKlinikeController {

	@Autowired
	private AdministratorKlinikeService adminKlinikeService;
	
	@Autowired
	private AdministratorKlinikeConversion adminKlinikeConversion;
		
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody AdminDTO adminKlinikeDTO) {
		try {
			this.adminKlinikeService.save(this.adminKlinikeConversion.get(adminKlinikeDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
}
