package com.example.demo.conversion.total;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.LekarDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class LekarConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;

	@Autowired
	private TipPoseteRepository tipPoseteRepository;

	@Autowired
	private LekarRepository lekarRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Transactional(readOnly = true)
	public Lekar get(LekarDTO lekarDTO) throws ParseException {

		long version;
		String lozinka;
		if (lekarDTO.getId() != null) {
			version = this.lekarRepository.getOne(lekarDTO.getId()).getVersion();		
			if (!lekarDTO.getLozinka().equals(this.lekarRepository.getOne(lekarDTO.getId()).getLozinka()))
				lozinka = this.passwordEncoder.encoder().encode(lekarDTO.getLozinka());
			else
				lozinka = lekarDTO.getLozinka();
		}
		else {
			version = 0l;	
			lozinka = this.passwordEncoder.encoder().encode(lekarDTO.getLozinka());
		}

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		String baseDate = "2020-04-20 ";

		return new Lekar(lekarDTO.getId(), lekarDTO.getEmail(), lozinka, lekarDTO.getIme(),
				lekarDTO.getPrezime(), lekarDTO.getTelefon(), lekarDTO.getDrzava(), lekarDTO.getGrad(),
				lekarDTO.getAdresa(), lekarDTO.isAktivan(), lekarDTO.isPromenjenaSifra(),
				f.parse(baseDate + lekarDTO.getPocetnoVreme()), f.parse(baseDate + lekarDTO.getKrajnjeVreme()),
				this.klinikaRepository.getOne(lekarDTO.getKlinika()),
				this.tipPoseteRepository.getOne(lekarDTO.getSpecijalizacija()), version);

	}

	public LekarDTO get(Lekar lekar) {
		return new LekarDTO(lekar);
	}

	public List<LekarDTO> get(List<Lekar> lekari) {

		List<LekarDTO> lekariDTO = new ArrayList<>();
		for (Lekar l : lekari)
			lekariDTO.add(new LekarDTO(l));
		Collections.sort(lekariDTO);
		return lekariDTO;

	}

}
