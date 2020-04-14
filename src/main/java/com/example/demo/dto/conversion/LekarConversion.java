package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.LekarDTO;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.TipPoseteRepository;
import com.example.demo.model.Lekar;

@Component
public class LekarConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;

	public Lekar get(LekarDTO lekarDTO) {
		Lekar lekar = new Lekar();
		lekar.setId(lekarDTO.getId());
		lekar.setEmail(lekarDTO.getEmail());
		lekar.setLozinka(lekarDTO.getLozinka());
		lekar.setIme(lekarDTO.getIme());
		lekar.setPrezime(lekarDTO.getPrezime());
		lekar.setTelefon(lekarDTO.getTelefon());
		lekar.setSpecijalizacija(this.tipPoseteRepository.getOne(lekarDTO.getSpecijalizacija()));
		lekar.setDrzava(lekarDTO.getDrzava());
		lekar.setAdresa(lekarDTO.getAdresa());
		lekar.setGrad(lekarDTO.getGrad());
		lekar.setKlinika(this.klinikaRepository.getOne(lekarDTO.getKlinika()));
		return lekar;
	}
	
	public LekarDTO get(Lekar lekar) {
		return new LekarDTO(lekar);
	}
	
	public List<LekarDTO> get(List<Lekar> lekari){
		
		List<LekarDTO> lekariDTO = new ArrayList<>();
		for (Lekar l: lekari)
			lekariDTO.add(new LekarDTO(l));
		return lekariDTO;
		
	}
}
