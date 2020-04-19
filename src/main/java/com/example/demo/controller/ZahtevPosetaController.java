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

import com.example.demo.dto.ZahtevPosetaDTO;
import com.example.demo.dto.conversion.ZahtevPosetaConversion;
import com.example.demo.model.Pacijent;
import com.example.demo.service.UserService;
import com.example.demo.service.ZahtevPosetaService;

@RestController
@RequestMapping(value="/zahtevPoseta")
public class ZahtevPosetaController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ZahtevPosetaConversion zahtevPosetaConversion;
	
	@Autowired
	private ZahtevPosetaService zahtevPosetaService;

	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/kreiraj", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody ZahtevPosetaDTO zahtevDTO){

		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			zahtevDTO.setKarton(pacijent.getKarton().getId());
			this.zahtevPosetaService.save(this.zahtevPosetaConversion.get(zahtevDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
