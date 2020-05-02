package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.example.demo.dto.unos.ZahtevPosetaObradaDTO;
import com.example.demo.model.korisnici.Admin;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.resursi.Sala;
import com.example.demo.service.KartonService;
import com.example.demo.service.LekarService;
import com.example.demo.service.PacijentService;
import com.example.demo.service.PosetaService;
import com.example.demo.service.SalaService;
import com.example.demo.service.UserService;
import com.example.demo.service.ZahtevPosetaService;

@RestController
@RequestMapping(value = "/sala")
public class SalaController {

	Date slobodan;

	@Autowired
	private SalaService salaService;

	@Autowired
	private UserService userService;

	@Autowired
	private ZahtevPosetaService zahtevPosetaService;

	@Autowired
	private LekarService lekarService;

	@Autowired
	private KartonService kartonService;

	@Autowired
	private PacijentService pacijentService;

	@Autowired
	private SalaConversion salaConversion;

	@Autowired
	private PosetaConversion posetaConversion;

	@Autowired
	private PosetaService posetaService;

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
	@GetMapping(value = "/admin/SlobodniTermin", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> kkk() {
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
			return new ResponseEntity<>(f.format(this.slobodan), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/admin/rezervacijaSale", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> reserve(@RequestBody ZahtevPosetaObradaDTO zahtevDTO) {
		SalaDTO salaDTO = new SalaDTO();
		try {
			zahtevDTO.osveziKraj();
			Sala sala = this.salaService.nadji(zahtevDTO.getIdSale());
			salaDTO = new SalaDTO(sala);
			Poseta poseta = this.posetaConversion.get(zahtevDTO, salaDTO);
			if (poseta != null) {
				this.posetaService.save2(poseta);
				sala.getPosete().add(poseta);
				this.salaService.save(sala);
				Lekar lekar = this.lekarService.nadji(zahtevDTO.getIdLekar());
				lekar.getPosete().add(poseta);
				this.lekarService.save(lekar);
				Pacijent pacijent = this.pacijentService.nadji(zahtevDTO.getIdPacijent());
				pacijent.getKarton().getPosete().add(poseta);
				this.kartonService.save(pacijent.getKarton());
				this.pacijentService.save(pacijent);
				this.zahtevPosetaService.obrisi(zahtevDTO.getId());
				// sacuvati salu
			} else { // znaci da nema termina
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
				Calendar pocetak = Calendar.getInstance();
				pocetak.setTime(f.parse(zahtevDTO.getDatum()));
				Calendar kraj = Calendar.getInstance();
				kraj.setTime(f.parse(zahtevDTO.getKraj()));
				while (!salaDTO.proveriDatum(pocetak.getTime(), kraj.getTime())) {
					pocetak.add(Calendar.HOUR, 1);
					kraj.add(Calendar.HOUR, 1);
				}
				salaDTO.setPrviSlobodan(pocetak.getTime());
				this.slobodan = salaDTO.getPrviSlobodan();
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
