package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Hibernate;
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

import com.example.demo.conversion.total.ZahtevOdmorConversion;
import com.example.demo.dto.model.ZahtevOdmorDTO;
import com.example.demo.dto.unos.ZahtevOdmorObradaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
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
	
	@Autowired
	private EmailService emailService;

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
	
	@PreAuthorize("hasAnyAuthority('Lekar','Sestra')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> kreiranje(@RequestBody ZahtevOdmorDTO zahtevOdmorDTO){
		Zaposleni zaposleni;
		ZahtevOdmor zahtevOdmor;
		try {
			zaposleni = (Zaposleni) this.userService.getSignedKorisnik();
			zahtevOdmorDTO.setZaposleni(zaposleni.getId());
			zahtevOdmorDTO.setKlinika(zaposleni.getKlinika().getId());
			zahtevOdmor = this.zahtevOdmorConversion.get(zahtevOdmorDTO);
			this.zahtevOdmorService.save(zahtevOdmor);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy.");
			String obavestenje = "Zaposleni " + zaposleni.getIme() + " " + zaposleni.getPrezime() + " zatrazio je "
					+ "godisnji odmor u periodu od " + f.format(zahtevOdmor.getPocetak()) + " do " + f.format(zahtevOdmor.getKraj()) + "\n";
			for (Zaposleni z: zaposleni.getKlinika().getZaposleni()) {
				z = (Zaposleni) Hibernate.unproxy(z);
				if (z instanceof Admin)
					this.emailService.sendMessage(new Message(z.getEmail(), "Zahtev za godisnji odmor", obavestenje));
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/potvrda", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> potvrda(@RequestBody ZahtevOdmorObradaDTO zahtevObrada){
		ZahtevOdmor zahtev;
		try {
			zahtev = this.zahtevOdmorService.nadji(zahtevObrada.getId());
			zahtev.setOdobren(true);
			this.zahtevOdmorService.save(zahtev);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy.");
			String obavestenje = "Vas zahtev za godisnji odmor u periodu od " + f.format(zahtev.getPocetak()) + ""
					+ " do " + f.format(zahtev.getKraj()) + " je odobren. \n";
			Message poruka = new Message(zahtev.getZaposleni().getEmail(), "Godisnji odmor odobren", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/odbijanje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> odbijanje(@RequestBody ZahtevOdmorObradaDTO zahtevObrada){
		ZahtevOdmor zahtev;
		try {
			zahtev = this.zahtevOdmorService.nadji(zahtevObrada.getId());
			this.zahtevOdmorService.delete(zahtev.getId());
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
			String obavestenje = "Vas zahtev za godisnji odmor u periodu od " + f.format(zahtev.getPocetak()) + ""
					+ " do " + f.format(zahtev.getKraj()) + " je odbijen, iz razloga: " + zahtevObrada.getRazlog() + "\n";
			Message poruka = new Message(zahtev.getZaposleni().getEmail(), "Godisnji odmor odbijen", obavestenje);
			this.emailService.sendMessage(poruka);
			return new ResponseEntity<>(HttpStatus.OK);			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);			
		}
	}
	
}
