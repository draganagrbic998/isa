package com.example.demo.dto.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.model.Lokacija;
import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.repository.LokacijaRepository;

@Component
public class ZahtevRegistracijaConversion {
	
	@Autowired
	private LokacijaRepository repository;
	
	//ovde sam rucno sve postavljala sa setom, jer cemo u drugim klasama, koje sadrze reference
	//na druge objekte, dobavljati te objekte i rucno setovati, pa sam htela da budem konzistentna
	public ZahtevRegistracija get(ZahtevRegistracijaDTO zahtevDTO) {
		ZahtevRegistracija zahtev = new ZahtevRegistracija();
		zahtev.setNoviEmail(zahtevDTO.getEmailDTO());
		zahtev.setNovaLozinka(zahtevDTO.getLozinkaDTO());
		zahtev.setNovoIme(zahtevDTO.getImeDTO());
		zahtev.setNovoPrezime(zahtevDTO.getPrezimeDTO());
		zahtev.setNoviTelefon(zahtevDTO.getTelefonDTO());
		zahtev.setNoviBrojOsiguranika(zahtevDTO.getBrojOsiguranikaDTO());
		
		Lokacija lokacija = null;
		for (Lokacija l: this.repository.findAll()) {
			if (l.getDrzava().equals(zahtevDTO.getDrzavaDTO()) && l.getGrad().equals(zahtevDTO.getGradDTO())
					&& l.getAdresa().equals(zahtevDTO.getAdresaDTO())) {
				lokacija = l;
				break;
			}
		}
		if (lokacija == null)
			lokacija = new Lokacija(zahtevDTO.getDrzavaDTO(), zahtevDTO.getGradDTO(), zahtevDTO.getAdresaDTO());
		
		zahtev.setLokacija(lokacija);
		return zahtev;
	}
	
	//ovo mislim da ce ovako uvek biti
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}
	
}
