package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.LekDTO;
import com.example.demo.model.Lek;

@Component
public class LekConversion {

	public Lek get(LekDTO lekDTO) {
		Lek lek = new Lek();
		lek.setId(lekDTO.getId());
		lek.setSifra(lekDTO.getSifra());
		lek.setNaziv(lekDTO.getNaziv());
		return lek;
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
