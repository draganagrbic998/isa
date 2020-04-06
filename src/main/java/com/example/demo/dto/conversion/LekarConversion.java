package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.LekarDTO;
import com.example.demo.model.Lokacija;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.model.Klinika;
import com.example.demo.model.Lekar;

@Component
public class LekarConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;

	public Lekar get(LekarDTO lekarDTO) {
		Lekar lekar = new Lekar();
		lekar.setId(lekarDTO.getId());
		lekar.setEmail(lekarDTO.getEmailLekar());
		lekar.setLozinka(lekarDTO.getLozinkaLekar());
		lekar.setIme(lekarDTO.getImeLekar());
		lekar.setPrezime(lekarDTO.getPrezimeLekar());
		lekar.setTelefon(lekarDTO.getTelefonLekar());
		lekar.setSpecijalizacija(lekarDTO.getNovaSpecijalizacija());
		lekar.setLokacija(new Lokacija(lekarDTO.getNovaDrzava(), lekarDTO.getNoviGrad(), lekarDTO.getNovaAdresa()));
		//ovo samo za sada ovako da mozemo da testiramo
		if (lekarDTO.getNovaKlinika() != null) {
			Klinika klinika = this.klinikaRepository.getOne(lekarDTO.getNovaKlinika());
			lekar.setKlinika(klinika);
		}
		return lekar;
	}
	
	public LekarDTO get(Lekar lekar) {
		return new LekarDTO(lekar);
	}
	
	public List<LekarDTO> get(List<Lekar> lekari){
		
		List<LekarDTO> lekariDTO = new ArrayList<LekarDTO>();
		for (Lekar l: lekari)
			lekariDTO.add(new LekarDTO(l));
		return lekariDTO;
		
	}
}
