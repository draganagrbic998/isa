package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.TerapijaDTO;
import com.example.demo.model.Terapija;

@Component
public class TerapijaConversion {
	
	public TerapijaDTO get(Terapija terapija) {
		return new TerapijaDTO(terapija);
	}
	
	public List<TerapijaDTO> get(List<Terapija> terapije){
		List<TerapijaDTO> terapijeDTO = new ArrayList<>();
		for (Terapija t: terapije)
			terapijeDTO.add(new TerapijaDTO(t));
		return terapijeDTO;
	}
}
