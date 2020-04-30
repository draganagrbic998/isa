package com.example.demo.conversion.partial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.PacijentDTO;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.repository.KartonRepository;
import com.example.demo.repository.PacijentRepository;

@Component
public class PacijentConversion {
	
	@Autowired
	private KartonRepository kartonRepository;
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Transactional(readOnly = true)
	public Pacijent get(PacijentDTO pacijentDTO) {
		
		long version;
		if (pacijentDTO.getId() != null)
			version = this.pacijentRepository.getOne(pacijentDTO.getId()).getVersion();
		else
			version = 0l;
		
		return new Pacijent(pacijentDTO.getId(), pacijentDTO.getEmail(), pacijentDTO.getLozinka(), 
				pacijentDTO.getIme(), pacijentDTO.getPrezime(), pacijentDTO.getTelefon(), 
				pacijentDTO.getDrzava(), pacijentDTO.getGrad(), pacijentDTO.getAdresa(), 
				pacijentDTO.isAktivan(), pacijentDTO.isPromenjenaSifra(), 
				this.kartonRepository.getOne(pacijentDTO.getKarton()), version);
		
	}
	
	public PacijentDTO get(Pacijent pacijent) {
		
		return new PacijentDTO(pacijent);
		
	}
	
}
