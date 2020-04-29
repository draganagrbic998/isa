package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.student1.KlinikaPretragaDTO;
import com.example.demo.model.Klinika;

@Component
public class KlinikaConversion {
	
	
	public Klinika get(KlinikaDTO klinikaDTO) {
		
		
		return new Klinika(klinikaDTO.getId(), klinikaDTO.getNaziv(), 
				klinikaDTO.getOpis(), klinikaDTO.getAdresa());
	}
	
	public KlinikaDTO get(Klinika klinika) {
		return new KlinikaDTO(klinika);
	}
	
	public List<KlinikaDTO> get(List<Klinika> klinike){
		
		List<KlinikaDTO> klinikeDTO = new ArrayList<>();
		for (Klinika k: klinike) 
			klinikeDTO.add(new KlinikaDTO(k));
		Collections.sort(klinikeDTO);
		return klinikeDTO;
		
	}
	
	public List<KlinikaPretragaDTO> getPretraga(List<Klinika> klinike) {

		List<KlinikaPretragaDTO> lista = new ArrayList<>();
		for (Klinika k: klinike)
			lista.add(new KlinikaPretragaDTO(k));
		Collections.sort(lista);
		return lista;
		
	}
	
}