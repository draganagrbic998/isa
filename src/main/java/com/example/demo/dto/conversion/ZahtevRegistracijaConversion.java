package com.example.demo.dto.conversion;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.model.Lokacija;
import com.example.demo.model.ZahtevRegistracija;

@Component
public class ZahtevRegistracijaConversion {
	
	public ZahtevRegistracija get(ZahtevRegistracijaDTO zahtevDTO) {
		
		ZahtevRegistracija zahtev = new ZahtevRegistracija();
		zahtev.setNoviEmail(zahtevDTO.getNoviEmail());
		zahtev.setNovaLozinka(zahtevDTO.getNovaLozinka());
		zahtev.setNovoIme(zahtevDTO.getNovoIme());
		zahtev.setNovoPrezime(zahtevDTO.getNovoPrezime());
		zahtev.setNoviTelefon(zahtevDTO.getNoviTelefon());
		zahtev.setNoviBrojOsiguranika(zahtevDTO.getNoviBrojOsiguranika());
		zahtev.setNovaLokacija(new Lokacija(zahtevDTO.getNovaDrzava(), zahtevDTO.getNoviGrad(), zahtevDTO.getNovaAdresa()));
		return zahtev;
		
	}
	
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}

}
