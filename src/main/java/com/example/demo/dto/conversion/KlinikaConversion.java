package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.Lokacija;

@Component
public class KlinikaConversion {

	public Klinika get(KlinikaDTO klinikaDTO) {
		Klinika klinika = new Klinika();
		klinika.setId(klinikaDTO.getId());
		klinika.setNaziv(klinikaDTO.getNaziv());
		klinika.setOpis(klinikaDTO.getOpis());
		klinika.setLokacija(new Lokacija(klinikaDTO.getNovaDrzava(), klinikaDTO.getNoviGrad(), klinikaDTO.getNovaAdresa()));
		return klinika;
	}
	
	public KlinikaDTO get(Klinika klinika) {
		return new KlinikaDTO(klinika);
	}
	
	public List<KlinikaDTO> get(List<Klinika> klinike){
		
		List<KlinikaDTO> lista = new ArrayList<KlinikaDTO>();
		for (Klinika k: klinike) 
			lista.add(new KlinikaDTO(k));
		return lista;
		
	}
	
}
