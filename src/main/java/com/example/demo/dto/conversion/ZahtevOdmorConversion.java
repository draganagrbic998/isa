package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevOdmorDTO;
import com.example.demo.model.ZahtevOdmor;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.ZaposleniRepository;

@Component
public class ZahtevOdmorConversion {
	
	@Autowired 
	private ZaposleniRepository zaposleniRepository;
	
	@Autowired 
	private KlinikaRepository klinikaRepository;
	
	
	public ZahtevOdmor get(ZahtevOdmorDTO zahtevDTO) {
		return new ZahtevOdmor(zahtevDTO.getId(), zahtevDTO.getPocetak(), zahtevDTO.getKraj(),
				zahtevDTO.isOdobren(),
				this.zaposleniRepository.getOne(zahtevDTO.getZaposleni()), 
				this.klinikaRepository.getOne(zahtevDTO.getKlinika()));
	}
	
	
	public ZahtevOdmorDTO get(ZahtevOdmor zahtev) {
		return new ZahtevOdmorDTO(zahtev);
	}
	
	public List<ZahtevOdmorDTO> get(List<ZahtevOdmor> zahtevi){
		
		List<ZahtevOdmorDTO> zahteviDTO = new ArrayList<>();
		for (ZahtevOdmor z: zahtevi)
			zahteviDTO.add(new ZahtevOdmorDTO(z));
		Collections.sort(zahteviDTO);
		return zahteviDTO;
	}
}
