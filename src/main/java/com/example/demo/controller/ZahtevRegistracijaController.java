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

import com.example.demo.dto.ObradaZahtevRegistracijaDTO;
import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.dto.conversion.ZahtevRegistracijaConversion;
import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.service.ZahtevRegistracijaService;

@RestController
@RequestMapping(value = "/zahtevRegistracija")
public class ZahtevRegistracijaController {
	
	@Autowired
	private ZahtevRegistracijaService zahtevRegistracijaService;
	
	@Autowired
	private ZahtevRegistracijaConversion zahtevRegistracijaConversion;
		
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZahtevRegistracijaDTO>> getZahteviRegistracija(){
		return new ResponseEntity<>(this.zahtevRegistracijaConversion.get(this.zahtevRegistracijaService.findAll()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody ZahtevRegistracijaDTO zahtevDTO) {

		this.zahtevRegistracijaService.save(this.zahtevRegistracijaConversion.get(zahtevDTO));
		return new ResponseEntity<>(HttpStatus.OK);
		
	}	
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/potvrda", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> potvrda(@RequestBody ObradaZahtevRegistracijaDTO obradaZahtevDTO) {
		ZahtevRegistracija zahtev = this.zahtevRegistracijaService.nadji(obradaZahtevDTO);
		
		if (zahtev == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		this.zahtevRegistracijaService.potvrdi(zahtev);

		try {
			this.zahtevRegistracijaService.delete(zahtev);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/odbijanje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> odbijanje(@RequestBody ObradaZahtevRegistracijaDTO obradaZahtevDTO) {
		ZahtevRegistracija zahtev = this.zahtevRegistracijaService.nadji(obradaZahtevDTO);
		
		if (zahtev == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		this.zahtevRegistracijaService.odbij(zahtev, obradaZahtevDTO.getRazlog());

		try {
			this.zahtevRegistracijaService.delete(zahtev);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
}
