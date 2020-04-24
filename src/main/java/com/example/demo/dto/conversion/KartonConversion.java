package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.KartonDTO;
import com.example.demo.model.Karton;
import com.example.demo.repository.PacijentRepository;

@Component
public class KartonConversion {
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	
	public Karton get(KartonDTO kartonDTO) {
		
		
		return new Karton(kartonDTO.getId(), kartonDTO.getBrojOsiguranika(), 
				kartonDTO.getVisina(), kartonDTO.getTezina(), 
				kartonDTO.getLevaDioptrija(), kartonDTO.getDesnaDioptrija(), 
				kartonDTO.getKrvnaGrupa(), this.pacijentRepository.getOne(kartonDTO.getPacijent()));
	}
		
	public KartonDTO get(Karton karton) {
		return new KartonDTO(karton);
	}
	
	public List<KartonDTO> get(List<Karton> kartoni){
		
		List<KartonDTO> kartoniDTO = new ArrayList<>();
		for (Karton k: kartoni)
			kartoniDTO.add(new KartonDTO(k));
		Collections.sort(kartoniDTO);
		return kartoniDTO;
		
	}

}