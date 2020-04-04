package com.example.demo.dto.conversion;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.model.Lokacija;
import com.example.demo.model.ZahtevRegistracija;

@Component
public class ZahtevRegistracijaConversion {
	
	public ZahtevRegistracija get(ZahtevRegistracijaDTO zahtevDTO) {
		
		ZahtevRegistracija zahtev = new ZahtevRegistracija();
		zahtev.setNoviEmail(zahtevDTO.getEmail());
		zahtev.setNovaLozinka(zahtevDTO.getLozinka());
		zahtev.setNovoIme(zahtevDTO.getIme());
		zahtev.setNovoPrezime(zahtevDTO.getPrezime());
		zahtev.setNoviTelefon(zahtevDTO.getTelefon());
		zahtev.setNoviBrojOsiguranika(zahtevDTO.getBrojOsiguranika());
		zahtev.setNovaLokacija(new Lokacija(zahtevDTO.getDrzava(), zahtevDTO.getGrad(), zahtevDTO.getAdresa()));
		return zahtev;
		
	}
	
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}

}
