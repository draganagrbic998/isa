package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TipPoseteDTO;
import com.example.demo.model.TipPosete;
import com.example.demo.repository.KlinikaRepository;

@Component
public class TipPoseteConversion {
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public TipPosete get(TipPoseteDTO tipPoseteDTO) {
		return new TipPosete(tipPoseteDTO.getId(), 
				tipPoseteDTO.getPregled(), 
				tipPoseteDTO.getNaziv(), 
				tipPoseteDTO.getSati(), 
				tipPoseteDTO.getMinute(), 
				tipPoseteDTO.getCena(), 
				this.klinikaRepository.getOne(tipPoseteDTO.getKlinika()));
	}
	
	public TipPoseteDTO get(TipPosete tipPosete) {
		return new TipPoseteDTO(tipPosete);
	}
	
	public List<TipPoseteDTO> get(List<TipPosete> tipoviPoseta) {
		List<TipPoseteDTO> tipoviPosetaDTO = new ArrayList<>();
		for (TipPosete tp : tipoviPoseta)
			tipoviPosetaDTO.add(new TipPoseteDTO(tp));
		return tipoviPosetaDTO;
	}

}
