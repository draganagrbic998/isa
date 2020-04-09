package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.dto.conversion.ZahtevRegistracijaConversion;
import com.example.demo.service.ZahtevRegistracijaService;

@RestController
@RequestMapping(value = "/zahtev/registracija")
public class ZahtevRegistracijaController {
	
	@Autowired
	private ZahtevRegistracijaService zahtevService;
	
	@Autowired
	private ZahtevRegistracijaConversion zahtevConversion;
		
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody ZahtevRegistracijaDTO zahtevDTO) {

		this.zahtevService.save(this.zahtevConversion.get(zahtevDTO));
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
