package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PacijentDTO;
import com.example.demo.model.Pacijent;
import com.example.demo.repository.KartonRepository;

@Component
public class PacijentConversion {
	
	@Autowired
	private KartonRepository kartonRepository;

	public Pacijent get(PacijentDTO pacijentDTO) {
		
		return new Pacijent(pacijentDTO.getId(), pacijentDTO.getEmail(), pacijentDTO.getLozinka(), 
				pacijentDTO.getIme(), pacijentDTO.getPrezime(), pacijentDTO.getTelefon(), 
				pacijentDTO.getDrzava(), pacijentDTO.getGrad(), pacijentDTO.getAdresa(), 
				pacijentDTO.isAktivan(), pacijentDTO.isPromenjenaSifra(), 
				this.kartonRepository.getOne(pacijentDTO.getKarton()));
		
	}
	
	public PacijentDTO get(Pacijent pacijent) {
		
		return new PacijentDTO(pacijent);
		
	}
	
	public List<PacijentDTO> get(List<Pacijent> pacijenti){
		
		List<PacijentDTO> pacijentiDTO = new ArrayList<>();
		for (Pacijent p: pacijenti)
			pacijentiDTO.add(new PacijentDTO(p));
		return pacijentiDTO;
		
	}
	
}