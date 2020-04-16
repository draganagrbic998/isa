package com.example.demo.dto.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.student1.ZahtevPosetaDTO;
import com.example.demo.model.Karton;
import com.example.demo.model.ZahtevPregled;
import com.example.demo.repository.LekarRepository;

@Component
public class ZahtevPosetaConversion {

	@Autowired
	private LekarRepository lekarRepository;
	
	public ZahtevPregled get(ZahtevPosetaDTO zahtevDTO, Karton karton) {
		
		ZahtevPregled zahtev = new ZahtevPregled();
		zahtev.setId(zahtevDTO.getId());
		zahtev.setLekar(this.lekarRepository.getOne(zahtevDTO.getLekar()));
		zahtev.setDatum(zahtevDTO.getDatum());
		zahtev.setKarton(karton);
		return zahtev;
		
	}
	
}
