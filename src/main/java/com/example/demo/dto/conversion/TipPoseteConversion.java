package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.TipPoseteDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.TipPosete;
import com.example.demo.repository.KlinikaRepository;

@Component
public class TipPoseteConversion {
	
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public TipPosete get(TipPoseteDTO tpDTO) {
		TipPosete tipPosete = new TipPosete();
		tipPosete.setId(tpDTO.getId());
		tipPosete.setNaziv(tpDTO.getNaziv());
		tipPosete.setCena(tpDTO.getCena());
		tipPosete.setMinuti(tpDTO.getMinuti());
		tipPosete.setSati(tpDTO.getSati());
		tipPosete.setPregled(tpDTO.getPregled());
		Klinika klinika = this.klinikaRepository.getOne(tpDTO.getKlinika());
		tipPosete.setKlinika(klinika);
		return tipPosete;
	}
	
	public TipPoseteDTO get(TipPosete tipPosete) {
		return new TipPoseteDTO(tipPosete);
	}
	
	public List<TipPoseteDTO> get(List<TipPosete> tipoviPosete) {
		List<TipPoseteDTO> tpDTO = new ArrayList<>();
		for (TipPosete tp : tipoviPosete) {
			tpDTO.add(new TipPoseteDTO(tp));
		}
		return tpDTO;
	}

}
