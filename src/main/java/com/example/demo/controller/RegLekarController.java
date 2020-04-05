package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Klinika;
import com.example.demo.dto.RegLekarDTO;
import com.example.demo.dto.conversion.RegLekarConversion;
import com.example.demo.service.RegLekarService;

@RestController
@RequestMapping(value = "/registracijaLekar")
public class RegLekarController {

	@Autowired
	private RegLekarService lekarService;
	
	@Autowired
	private RegLekarConversion lekarConversion;
		
	//nisam odradila validaciju sa serverske strane
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody RegLekarDTO lekarDTO) {
		this.lekarService.create(this.lekarConversion.get(lekarDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/brisanje", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> create(@RequestBody String email) {
		String emailV1 = email.replace("%40", "@");
		String emailCorrect = emailV1.substring(0, emailV1.length() - 1);
		this.lekarService.obrisiLekara(emailCorrect);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value="/dobaviKlinike", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Klinika> getKlinike() {
		return this.lekarService.getKlinike();
	}
}
