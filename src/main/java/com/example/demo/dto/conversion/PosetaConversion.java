package com.example.demo.dto.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.PregledAdmin;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;
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
	
	public Poseta get(PregledAdmin poseta) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		
		String proba = (f.format(poseta.getDatum()));
		String dat = proba.substring(0, proba.length()-6);
		Date datum = f.parse(dat+" "+poseta.getVreme());
		Poseta mojaPoseta =  new Poseta();
		mojaPoseta.setId(poseta.getId());
		mojaPoseta.setDatum(datum);
		mojaPoseta.setPopust(poseta.getPopust());
		mojaPoseta.setTipPosete(this.tipPoseteRepository.getOne(poseta.getTip()));
		mojaPoseta.setSala(this.salaRepository.getOne(poseta.getSala()));
		mojaPoseta.setStanje(StanjePosete.SLOBODNO);
		mojaPoseta.getLekari().add(this.lekarRepository.getOne(poseta.getLekar()));
		return mojaPoseta;
	}
}
