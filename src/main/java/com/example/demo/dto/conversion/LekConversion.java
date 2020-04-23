package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.LekDTO;
import com.example.demo.model.Lek;

@Component
public class LekConversion {
	

	public Lek get(LekDTO lekDTO) {
				
		return new Lek(lekDTO.getId(), 
				lekDTO.getSifra(), 
				lekDTO.getNaziv());
	}
	
	public LekDTO get(Lek lek) {
		return new LekDTO(lek);
	}
	
	public List<LekDTO> get(List<Lek> lekovi){
		List<LekDTO> lekoviDTO = new ArrayList<>();
		for (Lek l: lekovi)
			lekoviDTO.add(new LekDTO(l));
		return lekoviDTO;
	}
}
