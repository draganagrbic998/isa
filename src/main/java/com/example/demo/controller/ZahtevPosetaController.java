package com.example.demo.controller;

import java.text.SimpleDateFormat;

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

import com.example.demo.dto.ZahtevPosetaDTO;
import com.example.demo.dto.ZakaziPregledLekar;
import com.example.demo.dto.conversion.ZahtevPosetaConversion;
import com.example.demo.model.Admin;
import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.model.ZahtevPoseta;
import com.example.demo.model.Zaposleni;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import com.example.demo.service.ZahtevPosetaService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@RestController
@RequestMapping(value="/zahtevPoseta")
public class ZahtevPosetaController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ZahtevPosetaConversion zahtevPosetaConversion;
	
	@Autowired
	private ZahtevPosetaService zahtevPosetaService;
	
	@Autowired
	private EmailService emailService;

	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/kreiraj", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody ZahtevPosetaDTO zahtevDTO){
		
		ZahtevPoseta zahtev = null;
		try {
			Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
			zahtevDTO.setKarton(pacijent.getKarton().getId());
			zahtev = this.zahtevPosetaConversion.get(zahtevDTO);
			this.zahtevPosetaService.save(zahtev);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
			String obavestenje = "Pacijent sa brojem " + zahtev.getKarton().getBrojOsiguranika() + " zatrazio je datuma " + f.format(zahtev.getDatum()) + " pregled kod doktora " + zahtev.getLekar().getIme() + " " + zahtev.getLekar().getPrezime() + ". ";
			for (Zaposleni z: zahtev.getLekar().getKlinika().getZaposleni()) {
				z = (Zaposleni) Hibernate.unproxy(z);
				if (z instanceof Admin)
					this.emailService.sendMessage(new Message(z.getEmail(), "Zahtev za pregled kod lekara", obavestenje));
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	@PreAuthorize("hasAuthority('Lekar')")
	@PostMapping(value = "/lekar/zakazi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> zakaziLekar(@RequestBody ZakaziPregledLekar pregled){
		try {
			Lekar lekar = (Lekar) this.userService.getSignedKorisnik();
			this.zahtevPosetaService.save(this.zahtevPosetaConversion.get(pregled)); 
			Admin admin = this.adminService.nadjiAdminaKlinike(lekar.getKlinika());
			String obavestenje = "Lekar " + lekar.getIme()+ " " + lekar.getPrezime() + " zatrazio je pregled/operaciju datuma " + pregled.getDatum() + " u " + pregled.getVreme() + " sati.";
			//this.emailService.sendMessage(new Message(admin.getEmail(), "Zahtev za pregled kod lekara", obavestenje));
			return new ResponseEntity<>(HttpStatus.OK);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
