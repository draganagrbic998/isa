package com.example.demo.dto.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.LekarDTO;
import com.example.demo.model.Lekar;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class LekarConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
		
	public Lekar get(LekarDTO lekarDTO) throws ParseException {
		
		long version;
		if (lekarDTO.getId() != null)
			version = this.lekarRepository.getOne(lekarDTO.getId()).getVersion();
		else
			version = 0l;
		
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		String baseDate = "2020-04-20 ";
		
		return new Lekar(lekarDTO.getId(), lekarDTO.getEmail(), lekarDTO.getLozinka(), 
				lekarDTO.getIme(), lekarDTO.getPrezime(), lekarDTO.getTelefon(), 
				lekarDTO.getDrzava(), lekarDTO.getGrad(), lekarDTO.getAdresa(), 
				lekarDTO.isAktivan(), lekarDTO.isPromenjenaSifra(), 
				f.parse(baseDate + lekarDTO.getPocetnoVreme()), f.parse(baseDate + lekarDTO.getKrajnjeVreme()), 
				this.klinikaRepository.getOne(lekarDTO.getKlinika()), 
				this.tipPoseteRepository.getOne(lekarDTO.getSpecijalizacija()), version);
		
	}
	
	public LekarDTO get(Lekar lekar) {
		return new LekarDTO(lekar);
	}
	
	public List<LekarDTO> get(List<Lekar> lekari){
		
		List<LekarDTO> lekariDTO = new ArrayList<>();
		for (Lekar l: lekari)
			lekariDTO.add(new LekarDTO(l));
		Collections.sort(lekariDTO);
		return lekariDTO;
		
	}
	
}
