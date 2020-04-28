  
package com.example.demo.dto.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ZahtevPosetaDTO;
import com.example.demo.dto.ZakaziPregledLekar;
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
				zahtevDTO.getTipPosete() != null ? this.tipPoseteRepository.getOne(zahtevDTO.getTipPosete()) : 
					this.lekarRepository.getOne(zahtevDTO.getLekar()).getSpecijalizacija());
		
	}
	
	
	public ZahtevPoseta get(ZakaziPregledLekar poseta) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		
		String proba = (f.format(poseta.getDatum()));
		String dat = proba.substring(0, proba.length()-6);
		Date datum = f.parse(dat+" "+poseta.getVreme());
		return new ZahtevPoseta(poseta.getId(), datum,
				this.kartonRepository.getOne(poseta.getKarton()), 
				this.lekarRepository.getOne(poseta.getLekar()), 
				poseta.getTip() != null ? this.tipPoseteRepository.getOne(poseta.getTip()) : 
				this.lekarRepository.getOne(poseta.getLekar()).getSpecijalizacija());
		
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
