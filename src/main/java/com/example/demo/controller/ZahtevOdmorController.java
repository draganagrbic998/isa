package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ZahtevOdmorObrada;
import com.example.demo.dto.ZahtevRegistracijaObrada;
import com.example.demo.model.Admin;
import com.example.demo.model.Pacijent;
import com.example.demo.model.ZahtevOdmor;
import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.model.Zaposleni;
import com.example.demo.service.UserService;
import com.example.demo.service.ZahtevOdmorService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@RestController
@RequestMapping(value="/zahtevOdmor")
public class ZahtevOdmorController {
	
	@Autowired
	private UserService userService;

	@Autowired 
	private EmailService emailService;
	
	@Autowired 
	private  ZahtevOdmorService zahtevOdmorService;
	
	//metoda koja vraca listu DTOZahtevaOdmor na osnovu klinike
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value="/zahteviKlinika", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ZahtevOdmorObrada>> uzmiZahteveGod(){
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.zahtevOdmorService.zaObradu(admin.getKlinika()), HttpStatus.OK);
			
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/potvrda", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> potvrda(@RequestBody ZahtevOdmorObrada zahtevObrada){
		try {
			//ZahtevOdmor zahtev = this.zahtevOdmorService.nadji(zahtevObrada.getId());
			
			Zaposleni zaposleni = this.zahtevOdmorService.nadjiZaposlenog(zahtevObrada.getZaposleniId());
			System.out.println(zaposleni.getEmail());
			//Message message = new Message(zaposleni.getEmail(), "Zahtev odobren!",
			//		"Vas zahtev za godisnji odmor/odsustvo je odobren!");
			//this.emailService.sendMessage(message);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/odbijanje", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> odbijanje(@RequestBody ZahtevOdmorObrada zahtevObrada){
		try {
			//ZahtevOdmor zahtev = this.zahtevOdmorService.nadji(zahtevObrada.getId());
			Zaposleni zaposleni = this.zahtevOdmorService.nadjiZaposlenog(zahtevObrada.getZaposleniId());
			//Message message = new Message(zaposleni.getEmail(), "Zahtev odbijen!",
				//	"Vas zahtev za godisnji odmor/odsustvo je odbijen!\n Razlozi: ...");
			//this.emailService.sendMessage(message);
			this.zahtevOdmorService.delete(zahtevObrada.getZaposleniId());
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
