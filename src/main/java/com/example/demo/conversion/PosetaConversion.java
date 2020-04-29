package com.example.demo.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.pretraga.PosetaPretragaDTO;
import com.example.demo.dto.unos.PredefinisanaPosetaDTO;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.SalaRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class PosetaConversion {
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
	
	public Poseta get(PredefinisanaPosetaDTO poseta) throws ParseException {
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		String temp1 = f.format(poseta.getDatum());
		String temp2 = temp1.substring(0, temp1.length() - 6);
		return new Poseta(f.parse(temp2 + " " + poseta.getVreme()), poseta.getPopust(), 
				StanjePosete.SLOBODNO, this.tipPoseteRepository.getOne(poseta.getTipPregleda()), 
				this.salaRepository.getOne(poseta.getSala()), 
				this.lekarRepository.getOne(poseta.getLekar()));

	}
	

	public PosetaPretragaDTO get(Poseta poseta) {
		return new PosetaPretragaDTO(poseta);
	}
	
}
