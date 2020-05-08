package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.conversion.total.SalaConversion;
import com.example.demo.dto.model.SalaDTO;
import com.example.demo.dto.unos.ZahtevPregledObradaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Karton;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.model.resursi.Sala;
import com.example.demo.model.zahtevi.ZahtevPoseta;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.SalaRepository;
import com.example.demo.repository.ZahtevPosetaRepository;

@Component
@Transactional(readOnly = true)
public class ZahtevPosetaService {

	@Autowired
	private ZahtevPosetaRepository zahtevPosetaRepository;

	@Autowired
	private LekarRepository lekarRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private SalaConversion salaConversion;

	@Transactional(readOnly = false)
	public void save(ZahtevPoseta zahtev) {
		this.zahtevPosetaRepository.save(zahtev);
		Lekar l = zahtev.getLekar();
		if (!l.slobodan(zahtev.pocetak(), zahtev.kraj())) {
			throw new MyRuntimeException();
		}
		Karton k = zahtev.getKarton();
		if (!k.slobodan(zahtev.pocetak(), zahtev.kraj()))
			throw new MyRuntimeException();
		l.setPoslednjaIzmena(new Date());
		this.lekarRepository.save(l);
	}

	public List<ZahtevPregledObradaDTO> pregledi(Klinika klinika) {
		List<ZahtevPregledObradaDTO> zahtevi = new ArrayList<>();
		for (ZahtevPoseta z : this.zahtevPosetaRepository.findByKlinikaId(klinika.getId())) {
			if (z.getTipPosete().isPregled())
				zahtevi.add(new ZahtevPregledObradaDTO(z));
		}
		return zahtevi;
	}

	@Transactional(readOnly = true)
	public List<ZahtevPregledObradaDTO> operacije(Klinika klinika) {
		List<ZahtevPregledObradaDTO> zahtevi = new ArrayList<>();
		for (ZahtevPoseta z : this.zahtevPosetaRepository.findByKlinikaId(klinika.getId())) {
			if (!z.getTipPosete().isPregled())
				zahtevi.add(new ZahtevPregledObradaDTO(z));
		}
		return zahtevi;
	}

	@Transactional(readOnly = false)
	public void obrisi(Integer id) {
		this.zahtevPosetaRepository.deleteById(id);
	}

	@Transactional(readOnly = false)
	public ZahtevPoseta nadji(Integer id) {
		return this.zahtevPosetaRepository.getOne(id);
	}

	@Transactional(readOnly = true)
	public Map<Poseta, Integer> obradiAutomatski() throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		Map<Poseta, Integer> novePosete = new HashMap<>();

		for (ZahtevPoseta z : this.zahtevPosetaRepository.findAll()) {
			Date najboljiTermin = null;
			Sala najboljaSala = null;

			for (Sala s : this.salaRepository.findAll()) {
				SalaDTO salaDTO = salaConversion.get(s);
				salaDTO.nadjiSlobodanTermin(f.format(z.getDatum()), f.format(z.kraj()), z.getLekar());
				Date trenutni = salaDTO.getPrviSlobodan();

				if (najboljiTermin == null || trenutni.before(najboljiTermin)) {
					najboljiTermin = trenutni;
					najboljaSala = s;
				}
			}
			
			novePosete.put(new Poseta(StanjePosete.ZAUZETO, najboljiTermin, null, 
					z.getTipPosete(), najboljaSala, z.getKarton(), 
					z.getLekar()), z.getId());
		}

		return novePosete;
	}
}
