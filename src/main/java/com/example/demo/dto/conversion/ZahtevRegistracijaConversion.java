package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.model.ZahtevRegistracija;

@Component
public class ZahtevRegistracijaConversion {
	
	public ZahtevRegistracija get(ZahtevRegistracijaDTO zahtevDTO) {
		
		return new ZahtevRegistracija(zahtevDTO.getId(), 
				zahtevDTO.getEmail(), 
				zahtevDTO.getLozinka(), 
				zahtevDTO.getIme(), 
				zahtevDTO.getPrezime(), 
				zahtevDTO.getTelefon(), 
				zahtevDTO.getBrojOsiguranika(), 
				zahtevDTO.getDrzava(), 
				zahtevDTO.getGrad(), 
				zahtevDTO.getAdresa());
		
	}
	
	public ZahtevRegistracijaDTO get(ZahtevRegistracija zahtev) {
		return new ZahtevRegistracijaDTO(zahtev);
	}
	
	public List<ZahtevRegistracijaDTO> get(List<ZahtevRegistracija> zahtevi){
		
		List<ZahtevRegistracijaDTO> zahteviDTO = new ArrayList<>();
		for (ZahtevRegistracija z: zahtevi)
			zahteviDTO.add(new ZahtevRegistracijaDTO(z));
		return zahteviDTO;
		
	}

}
