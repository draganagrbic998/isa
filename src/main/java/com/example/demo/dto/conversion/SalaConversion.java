package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.SalaDTO;
import com.example.demo.model.Sala;
import com.example.demo.repository.KlinikaRepository;

@Component
public class SalaConversion {
	
	@Autowired KlinikaRepository klinikaRepository;
	
	public Sala get(SalaDTO salaDTO) {
		Sala sala = new Sala();
		sala.setId(salaDTO.getId());
		sala.setBroj(salaDTO.getBroj());
		sala.setKlinika(this.klinikaRepository.getOne(salaDTO.getKlinika()));
		sala.setNaziv(salaDTO.getNaziv());
		return sala;
	}
	
	public SalaDTO get(Sala sala) {
		return new SalaDTO(sala);
	}
	
	public List<SalaDTO> get(List<Sala> sale) {
		List<SalaDTO> saleDTO = new ArrayList<>();
		for (Sala s : sale) 
			saleDTO.add(new SalaDTO(s));
		return saleDTO;
 	}
	

}
