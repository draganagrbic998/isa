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

import com.example.demo.dto.ZahtevOdmorDTO;
import com.example.demo.dto.ZahtevOdmorObradaDTO;
import com.example.demo.dto.conversion.ZahtevOdmorConversion;
import com.example.demo.model.Admin;
import com.example.demo.model.ZahtevOdmor;
import com.example.demo.service.UserService;
import com.example.demo.service.ZahtevOdmorService;

@RestController
@RequestMapping(value="/zahtevOdmor")
public class ZahtevOdmorController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private ZahtevOdmorService zahtevOdmorService;

	@Autowired 
	private ZahtevOdmorConversion zahtevOdmorConversion;
	
	

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value="/klinika/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZahtevOdmorDTO>> pregled(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.zahtevOdmorService.findAll(admin.getKlinika()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value = "/kreiranje", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody ZahtevOdmorDTO zahtevOdmorDTO){
		try {
			this.zahtevOdmorService.save(this.zahtevOdmorConversion.get(zahtevOdmorDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/potvrda", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> potvrda(@RequestBody ZahtevOdmorObradaDTO zahtevObrada){
		try {
			ZahtevOdmor zahtev = this.zahtevOdmorService.nadji(zahtevObrada.getId());
			zahtev.setOdobren(true);
			this.zahtevOdmorService.save(zahtev);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/odbijanje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> odbijanje(@RequestBody ZahtevOdmorObradaDTO zahtevObrada){
		try {
			ZahtevOdmor zahtev = this.zahtevOdmorService.nadji(zahtevObrada.getId());
			this.zahtevOdmorService.delete(zahtev.getId());
			return new ResponseEntity<>(HttpStatus.OK);			
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
