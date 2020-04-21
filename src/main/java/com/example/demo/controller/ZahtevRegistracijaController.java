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

import com.example.demo.dto.ObradaZahtevRegistracija;
import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.dto.conversion.ZahtevRegistracijaConversion;
import com.example.demo.model.Pacijent;
import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.service.ZahtevRegistracijaService;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@RestController
@RequestMapping(value = "/zahtevRegistracija")
public class ZahtevRegistracijaController {
	
	@Autowired
	private ZahtevRegistracijaService zahtevRegistracijaService;
	
	@Autowired
	private ZahtevRegistracijaConversion zahtevRegistracijaConversion;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ApplicationName name;

		
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
	public ResponseEntity<HttpStatus> potvrda(@RequestBody ObradaZahtevRegistracija obradaZahteva) {
		try {
			ZahtevRegistracija zahtev = this.zahtevRegistracijaService.nadji(obradaZahteva.getId());
			Pacijent pacijent = this.zahtevRegistracijaService.potvrdi(zahtev);
			Message message = new Message(pacijent.getEmail(), "Registracija Uspesna!",
					"Molimo Vas da aktivirate svoj nalog klikom na link:\n" + this.name.getName() + "/#/aktivirajNalog?id=" + pacijent.getId());
			this.emailService.sendMessage(message);
			return new ResponseEntity<>(HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('SuperAdmin')")
	@PostMapping(value = "/odbijanje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> odbijanje(@RequestBody ObradaZahtevRegistracija obradaZahteva) {
		try {
			ZahtevRegistracija zahtev = this.zahtevRegistracijaService.nadji(obradaZahteva.getId());
			this.zahtevRegistracijaService.delete(zahtev.getId());
			Message message = new Message(zahtev.getEmail(), "Registracija Odbijena!",
					"Vasa registracija je odbijena iz sledecih razloga:\n\n" + obradaZahteva.getRazlog()
							+ "\n\nMolimo Vas da pokusate ponovo.");
			this.emailService.sendMessage(message);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
}
