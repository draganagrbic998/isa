package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdministratorKlinikeDTO;
import com.example.demo.dto.conversion.AdministratorKlinikeConversion;
import com.example.demo.service.AdministratorKlinikeService;

@RestController
@RequestMapping(value = "/adminKlinike")
public class AdministratorKlinikeController {

	@Autowired
	private AdministratorKlinikeService adminKlinikeService;
	
	@Autowired
	private AdministratorKlinikeConversion adminKlinikeConversion;
		
	//nisam odradila validaciju sa serverske strane
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody AdministratorKlinikeDTO adminKlinikeDTO) {
		this.adminKlinikeService.create(this.adminKlinikeConversion.get(adminKlinikeDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
