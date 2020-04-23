package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.student1.KlinikaPretraga;
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
		return klinikeDTO;
		
	}
	
	public List<KlinikaPretraga> getPretraga(List<Klinika> klinike) {

		List<KlinikaPretraga> lista = new ArrayList<>();
		for (Klinika k: klinike)
			lista.add(new KlinikaPretraga(k));
		return lista;
		
	}
	
}