package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TerapijaDTO;
import com.example.demo.dto.conversion.TerapijaConversion;
import com.example.demo.model.Sestra;
import com.example.demo.service.TerapijaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/terapija")
public class TerapijaController {
	
		
		@Autowired
		private TerapijaService terapijaService;
		
		@Autowired
		private TerapijaConversion terapijaConversion;
		
		@Autowired
		private UserService userService;
		
		@PreAuthorize("hasAuthority('Sestra')")
		@GetMapping(value = "/getNeoverene", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<TerapijaDTO>> getNeovereneTerapije(){
			Sestra sestra = (Sestra) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.terapijaConversion.get(this.terapijaService.nadjiNeoverene(sestra)), HttpStatus.OK);
		}

}
