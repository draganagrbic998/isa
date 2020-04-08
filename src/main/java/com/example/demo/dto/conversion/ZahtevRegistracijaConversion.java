package com.example.demo.dto.conversion;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.model.ZahtevRegistracija;

@Component
public class ZahtevRegistracijaConversion {
	
	public ZahtevRegistracija get(ZahtevRegistracijaDTO zahtevDTO) {
		
		ZahtevRegistracija zahtev = new ZahtevRegistracija();
		zahtev.setId(zahtevDTO.getId());
		zahtev.setEmail(zahtevDTO.getEmail());
		zahtev.setLozinka(zahtevDTO.getLozinka());
		zahtev.setIme(zahtevDTO.getIme());
		zahtev.setPrezime(zahtevDTO.getPrezime());
		zahtev.setTelefon(zahtevDTO.getTelefon());
		zahtev.setBrojOsiguranika(zahtevDTO.getBrojOsiguranika());
		zahtev.setDrzava(zahtevDTO.getDrzava());
		zahtev.setGrad(zahtevDTO.getGrad());
		zahtev.setAdresa(zahtevDTO.getAdresa());
		return zahtev;
		
	}
	
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}

}
