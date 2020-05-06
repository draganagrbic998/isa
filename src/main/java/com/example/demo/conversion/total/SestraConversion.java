package com.example.demo.conversion.total;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.SestraDTO;
import com.example.demo.model.korisnici.Sestra;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.SestraRepository;

@Component
public class SestraConversion {

	@Autowired
	private SestraRepository sestraRepository;

	@Autowired
	private KlinikaRepository klinikaRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;

	private final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
	private final String baseDate = "2020-04-20 ";
	
	@Transactional(readOnly = true)
	public Sestra get(SestraDTO sestraDTO) throws ParseException {
				
		String lozinka;
		long version;
		
		if (sestraDTO.getId() != null) {
			version = this.sestraRepository.getOne(sestraDTO.getId()).getVersion();
			if (!sestraDTO.getLozinka().equals(this.sestraRepository.getOne(sestraDTO.getId()).getLozinka()))
				lozinka = this.passwordEncoder.encoder().encode(sestraDTO.getLozinka());
			else
				lozinka = sestraDTO.getLozinka();
		}
		else {
			version = 0l;
			lozinka = this.passwordEncoder.encoder().encode(sestraDTO.getLozinka());
		}
		
		return new Sestra(sestraDTO.getId(), 
				sestraDTO.getEmail(), 
				lozinka, 
				sestraDTO.getIme(), 
				sestraDTO.getPrezime(), 
				sestraDTO.getTelefon(), 
				sestraDTO.getDrzava(), 
				sestraDTO.getGrad(), 
				sestraDTO.getAdresa(), 
				sestraDTO.isAktivan(), 
				sestraDTO.isPromenjenaSifra(), 
				this.f.parse(baseDate + sestraDTO.getPocetnoVreme()), 
				this.f.parse(baseDate + sestraDTO.getKrajnjeVreme()), 
				this.klinikaRepository.getOne(sestraDTO.getKlinika()), version);
		
	}
	
	public SestraDTO get(Sestra sestra) {
		return new SestraDTO(sestra);
	}
	
	public List<SestraDTO> get(List<Sestra> sestre){
		
		List<SestraDTO> sestreDTO = new ArrayList<>();
		for (Sestra s: sestre)
			sestreDTO.add(new SestraDTO(s));
		Collections.sort(sestreDTO);
		return sestreDTO;
		
	}
	
}
