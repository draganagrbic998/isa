package com.example.demo.dto.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.SestraDTO;
import com.example.demo.model.Sestra;
import com.example.demo.repository.KlinikaRepository;

@Component
public class SestraConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	
	public Sestra get(SestraDTO sestraDTO) throws ParseException {
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		String baseDate = "2020-04-20 ";
		
		return new Sestra(sestraDTO.getId(), sestraDTO.getEmail(), sestraDTO.getLozinka(), 
				sestraDTO.getIme(), sestraDTO.getPrezime(), sestraDTO.getTelefon(), 
				sestraDTO.getDrzava(), sestraDTO.getGrad(), sestraDTO.getAdresa(), 
				sestraDTO.isAktivan(), sestraDTO.isPromenjenaSifra(), 
				f.parse(baseDate + sestraDTO.getPocetnoVreme()), f.parse(baseDate + sestraDTO.getKrajnjeVreme()), 
				this.klinikaRepository.getOne(sestraDTO.getKlinika()));
		
	}
	
	public SestraDTO get(Sestra sestra) {
		
		return new SestraDTO(sestra);
		
	}
	
	public List<SestraDTO> get(List<Sestra> sestre){
		
		List<SestraDTO> sestreDTO = new ArrayList<>();
		for (Sestra s: sestre)
			sestreDTO.add(new SestraDTO(s));
		return sestreDTO;
		
	}
	
}
