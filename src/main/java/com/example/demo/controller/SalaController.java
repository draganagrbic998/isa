package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.conversion.partial.PosetaConversion;
import com.example.demo.conversion.total.SalaConversion;
import com.example.demo.dto.model.SalaDTO;
import com.example.demo.dto.pretraga.GetPrviSlobodanDTO;
import com.example.demo.dto.unos.ZahtevOperacijaObradaDTO;
import com.example.demo.dto.unos.ZahtevPosetaObradaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.resursi.Sala;
import com.example.demo.service.EmailService;
import com.example.demo.service.LekarService;
import com.example.demo.service.Message;
import com.example.demo.service.PacijentService;
import com.example.demo.service.PosetaService;
import com.example.demo.service.SalaService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/sala")
public class SalaController {
	
	@Autowired
	private LekarService lekarService;

	@Autowired
	private SalaService salaService;

	@Autowired
	private UserService userService;

	@Autowired
	private PacijentService pacijentService;

	@Autowired
	private SalaConversion salaConversion;

	@Autowired
	private PosetaConversion posetaConversion;

	@Autowired
	private PosetaService posetaService;

	@Autowired
	private EmailService emailService;

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody SalaDTO salaDTO) {
		try {
			this.salaService.save(this.salaConversion.get(salaDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/admin/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> getS() {
		try {
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			return new ResponseEntity<>(this.salaConversion.get(this.salaService.findAll(admin)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/pregledSlobodne", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> pregled(@RequestBody ZahtevPosetaObradaDTO zahtev) {
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
			zahtev.osveziKraj();
			Date pocetak = f.parse(zahtev.getDatum());
			Date kraj = f.parse(zahtev.getKraj());
			List<SalaDTO> rezultat = new ArrayList<SalaDTO>();
			Admin admin = (Admin) this.userService.getSignedKorisnik();
			List<SalaDTO> lista = this.salaConversion.get(this.salaService.findAll(admin));
			for (SalaDTO sala : lista) {
				if (sala.proveriDatum(pocetak, kraj)) {
					rezultat.add(sala);
				}
			}
			return new ResponseEntity<>(rezultat, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/getPrviSlobodan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getPrviSlobodan(@RequestBody GetPrviSlobodanDTO data) throws ParseException {		
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
			data.getZahtev().osveziKraj();
			
			SalaDTO sala = this.salaConversion.get(this.salaService.nadji(data.getSalaId()));
			
			List<Lekar> lekari = lekarService.nadji(data.getLekari());
			
			sala.nadjiSlobodanTermin(data.getZahtev().getDatum(), data.getZahtev().getKraj(), lekari);
			return new ResponseEntity<>(f.format(sala.getPrviSlobodan()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/rezervacijaSale", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> reserve(@RequestBody ZahtevPosetaObradaDTO zahtevDTO) {
		SalaDTO salaDTO = new SalaDTO();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		Date slobodan = null;
		try {
			Lekar lekar = this.lekarService.nadji(zahtevDTO.getIdLekar());
			Pacijent pacijent = this.pacijentService.nadji(zahtevDTO.getIdPacijent());
			zahtevDTO.osveziKraj();
			salaDTO = new SalaDTO(this.salaService.nadji(zahtevDTO.getIdSale()));
			Poseta poseta = this.posetaConversion.get(zahtevDTO, salaDTO);
			salaDTO.nadjiSlobodanTermin(zahtevDTO.getDatum(),zahtevDTO.getKraj(), lekar);
			slobodan = salaDTO.getPrviSlobodan();
			if (poseta != null ) { 
				this.posetaService.save(poseta, zahtevDTO.getId(), true);
				String obavestenjePacijentu = "Postovani\n, pregled "+ zahtevDTO.getNaziv()+ " kod lekara " + zahtevDTO.getLekar() + " zakazan je za datum "+zahtevDTO.getDatum();
				String obavestenjeLekaru = "Postovani\n, pregled"+ zahtevDTO.getNaziv()+ " za pacijenta " + zahtevDTO.getPacijent() + " zakazan je za datum "+zahtevDTO.getDatum();
				Message porukaPacijent = new Message(pacijent.getEmail(), "Obavestenje o zakazanom pregledu", obavestenjePacijentu);
				Message porukaLekar = new Message(lekar.getEmail(), "Obavestenje o zakazanom pregledu", obavestenjeLekaru);
				this.emailService.sendMessage(porukaPacijent);
				this.emailService.sendMessage(porukaLekar);
				return new ResponseEntity<>(f.format(slobodan), HttpStatus.OK);
			} else { 
				return new ResponseEntity<>(f.format(slobodan), HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			slobodan = salaDTO.getPrviSlobodan();
			return new ResponseEntity<>(f.format(slobodan), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/rezervacijaSaleOperacije", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> reserveOperacija(@RequestBody ZahtevOperacijaObradaDTO zahtevDTO) {
		SalaDTO salaDTO = new SalaDTO();
		try {
			Sala sala = this.salaService.nadji(zahtevDTO.getSalaId());
			salaDTO = new SalaDTO(sala);

			Poseta poseta = this.posetaConversion.get(zahtevDTO, salaDTO);
			if (!poseta.getTipPosete().isPregled()) {
				this.posetaService.save(poseta, zahtevDTO.getId(), true);
			}
			else {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			if (!zahtevDTO.getPocetak().equals(zahtevDTO.getPocetakOriginalni())) {
				Pacijent p = pacijentService.nadji(zahtevDTO.getPacijentId());

				String obavestenje = "Operacija zakazana za " + zahtevDTO.getPocetakOriginalni() + " pomerena je za "
						+ zahtevDTO.getPocetak();

				Message poruka = new Message(p.getEmail(), "Pomeranje zakazane operacije", obavestenje);
				this.emailService.sendMessage(poruka);
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAuthority('Admin')")
	@DeleteMapping(value = "/brisanje/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
		try {
			this.salaService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
