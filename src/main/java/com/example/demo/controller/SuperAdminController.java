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

import com.example.demo.conversion.all.SuperAdminConversion;
import com.example.demo.dto.model.SuperAdminDTO;
import com.example.demo.service.SuperAdminService;

@RestController
@RequestMapping(value = "/superAdmin")
public class SuperAdminController {

	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private SuperAdminConversion superAdminConversion;
		
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody SuperAdminDTO superAdminDTO) {
		try {
			this.superAdminService.save(this.superAdminConversion.get(superAdminDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}	
	
}
