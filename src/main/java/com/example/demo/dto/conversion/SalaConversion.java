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
	
	@Autowired 
	private KlinikaRepository klinikaRepository;
	
	public Sala get(SalaDTO salaDTO) {
		return new Sala(salaDTO.getId(), 
				salaDTO.getBroj(), 
				salaDTO.getNaziv(), 
				this.klinikaRepository.getOne(salaDTO.getKlinika()));
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