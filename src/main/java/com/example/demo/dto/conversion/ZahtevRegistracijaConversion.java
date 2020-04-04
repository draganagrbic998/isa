package com.example.demo.dto.conversion;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.model.ZahtevRegistracija;

@Component
public class ZahtevRegistracijaConversion {

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
		return zahtev;
	}
	
	//ovo mislim da ce ovako uvek biti
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}
	
}
