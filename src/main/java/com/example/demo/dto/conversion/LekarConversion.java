package com.example.demo.dto.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.LekarDTO;
import com.example.demo.model.Lekar;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class LekarConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	
	public Lekar get(LekarDTO lekarDTO) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		String baseDate = "2020-04-20 ";

		Lekar lekar = new Lekar();
		lekar.setPocetnoVreme(format.parse(baseDate+lekarDTO.getPocetnoVreme()));
		lekar.setKrajnjeVreme(format.parse(baseDate+lekarDTO.getKrajnjeVreme()));
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
