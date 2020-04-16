package com.example.demo.controller;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.conversion.KlinikaConversion;
import com.example.demo.dto.student1.Bolest;
import com.example.demo.dto.student1.KlinikaPretraga;
import com.example.demo.dto.student1.KlinikaSlobodno;
import com.example.demo.dto.student1.OcenaParam;
import com.example.demo.dto.student1.PretragaParam;
import com.example.demo.model.Admin;
import com.example.demo.model.Pacijent;
import com.example.demo.service.KlinikaService;
import com.example.demo.service.UserService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@RestController
@RequestMapping(value = "/klinika")
public class KlinikaController {

	@Autowired
	private UserService userService;

	@Autowired
	private KlinikaService klinikaService;

	@Autowired
	private KlinikaConversion klinikaConversion;
	
	@Autowired
	private EmailService emailService;
	

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/vratiKliniku", produces = MediaType.APPLICATION_JSON_VALUE)
	public KlinikaDTO getClinic(){
		Admin loggedUser = (Admin) userService.getSignedKorisnik();
		return this.klinikaConversion.get(loggedUser.getKlinika());
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@GetMapping(value = "/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KlinikaDTO> review(){
		return this.klinikaConversion.get(this.klinikaService.findAll());
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody KlinikaDTO klinikaDTO) {
		this.klinikaService.save(this.klinikaConversion.get(klinikaDTO));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value = "/ocenjivanje/{posetaId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Bolest oceni(@PathVariable Integer posetaId, @RequestBody OcenaParam param){
		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
		return new Bolest(this.klinikaService.oceni(pacijent, param, posetaId));
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/slobodno" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KlinikaSlobodno> pregledSlobodno(){
		return this.klinikaService.slobodno();
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value = "/zakazi/{posetaId}")
	public KlinikaSlobodno zakaziSlobodno(@PathVariable Integer posetaId){

		Pacijent pacijent = (Pacijent) this.userService.getSignedKorisnik();
		this.klinikaService.zakazi(posetaId, pacijent.getKarton());
		this.emailService.sendMessage(new Message(pacijent.getEmail(), "Termin zakazan", "Zatrazeni termin je zakazan. "));
		return this.klinikaService.getKlinikaSlobodno(posetaId);

	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KlinikaPretraga> pretraga(){
		return this.klinikaConversion.getPretraga(this.klinikaService.findAll());
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@PostMapping(value="/pretraga", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<KlinikaPretraga> pretragaParam(@RequestBody PretragaParam param){
		

		
		return this.klinikaService.pretraga(param);
		
	}
	
}
