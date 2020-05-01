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

import com.example.demo.conversion.total.ZahtevPosetaConversion;
import com.example.demo.dto.model.ZahtevPosetaDTO;
import com.example.demo.dto.unos.ZahtevPosetaObradaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Korisnik;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.service.EmailService;
import com.example.demo.service.Message;
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
	
	@Autowired
	private EmailService emailService;

	@PreAuthorize("hasAnyAuthority('Lekar','Pacijent')")
	@PostMapping(value="/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody ZahtevPosetaDTO zahtevDTO){
		
		ZahtevPoseta zahtev = null;
		try {
			Korisnik korisnik = this.userService.getSignedKorisnik();
			if (korisnik instanceof Pacijent)
				zahtevDTO.setKarton(((Pacijent) korisnik).getKarton().getId());
			else{
				zahtevDTO.setKarton(((Lekar) korisnik).getZapocetaPoseta().getKarton().getId());
				zahtevDTO.setLekar(korisnik.getId());
			}
			zahtev = this.zahtevPosetaConversion.get(zahtevDTO);
			this.zahtevPosetaService.save(zahtev);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
			String obavestenje = "Stigao je zahtev za posetu tipa " + zahtev.getTipPosete().getNaziv() + " datuma " + f.format(zahtev.getDatum()) + ""
					+ ", kod lekara " + zahtev.getLekar().getIme() + " " + zahtev.getLekar().getPrezime() + ". Leci se pacijent sa " + ""
							+ zahtev.getKarton().getBrojOsiguranika() + " brojem osiguranika. \n";
			for (Zaposleni z: zahtev.getLekar().getKlinika().getZaposleni()) {
				z = (Zaposleni) Hibernate.unproxy(z);
				if (z instanceof Admin)
					this.emailService.sendMessage(new Message(z.getEmail(), "Poslat zahtev za posetu", obavestenje));
			}
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value="/klinika/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZahtevPosetaObradaDTO>> pregled(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.zahtevPosetaService.findAll(admin.getKlinika()), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
