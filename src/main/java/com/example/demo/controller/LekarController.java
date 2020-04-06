package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LekarDTO;
import com.example.demo.dto.conversion.LekarConversion;
import com.example.demo.service.LekarService;

@RestController
@RequestMapping(value = "/lekar")
public class LekarController {

	@Autowired
	private LekarService lekarService;
	
	@Autowired
	private LekarConversion lekarConversion;
		
	//nisam odradila validaciju sa serverske strane
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody LekarDTO lekarDTO) {
		this.lekarService.create(this.lekarConversion.get(lekarDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/brisanje/{lekarId}")
	public ResponseEntity<?> delete(@PathVariable Integer lekarId){
		this.lekarService.delete(lekarId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	
//	@PostMapping(value = "/brisanje", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	public ResponseEntity<?> create(@RequestBody String email) {
//		String replaced = email.replace("%40", "@");
//		String emailCorrect = replaced.substring(0, replaced.length() - 1);
//		boolean isDone = this.lekarService.obrisiLekara(emailCorrect);
//		if (isDone) {
//			return new ResponseEntity<>(HttpStatus.OK);
//		}
//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	}
	
	
}
