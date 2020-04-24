package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevPosetaDTO;
import com.example.demo.model.ZahtevPoseta;
import com.example.demo.repository.KartonRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class ZahtevPosetaConversion {

	@Autowired
	private KartonRepository kartonRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;
		
	public ZahtevPoseta get(ZahtevPosetaDTO zahtevDTO) {
				
		return new ZahtevPoseta(zahtevDTO.getId(), zahtevDTO.getDatum(), 
				this.kartonRepository.getOne(zahtevDTO.getKarton()), 
				this.lekarRepository.getOne(zahtevDTO.getLekar()), 
				zahtevDTO.getTipPosete() != null ? this.tipPoseteRepository.getOne(zahtevDTO.getTipPosete()) : null);
		
	}
	
	public ZahtevPosetaDTO get(ZahtevPoseta zahtev) {
		
		return new ZahtevPosetaDTO(zahtev);
		
	}
	
	public List<ZahtevPosetaDTO> get(List<ZahtevPoseta> zahtevi){
		
		List<ZahtevPosetaDTO> zahteviDTO = new ArrayList<>();
		for (ZahtevPoseta z: zahtevi)
			zahteviDTO.add(new ZahtevPosetaDTO(z));
		Collections.sort(zahteviDTO);
		return zahteviDTO;
	}
	
}
