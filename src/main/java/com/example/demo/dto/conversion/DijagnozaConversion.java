package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.DijagnozaDTO;
import com.example.demo.model.Dijagnoza;

@Component
public class DijagnozaConversion {

	public Dijagnoza get(DijagnozaDTO dijagnozaDTO) {
		Dijagnoza dijagnoza = new Dijagnoza();
		dijagnoza.setId(dijagnozaDTO.getId());
		dijagnoza.setSifra(dijagnozaDTO.getSifra());
		dijagnoza.setNaziv(dijagnozaDTO.getNaziv());
		return dijagnoza;
	}
	
	public DijagnozaDTO get(Dijagnoza dijagnoza) {
		return new DijagnozaDTO(dijagnoza);
	}
	
	public List<DijagnozaDTO> get(List<Dijagnoza> dijagnoze){
		List<DijagnozaDTO> dijagnozeDTO = new ArrayList<>();
		for (Dijagnoza d: dijagnoze)
			dijagnozeDTO.add(new DijagnozaDTO(d));
		return dijagnozeDTO;
	}
}
