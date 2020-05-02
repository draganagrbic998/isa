package com.example.demo.conversion.partial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.model.SalaDTO;
import com.example.demo.dto.pretraga.PosetaPretragaDTO;
import com.example.demo.dto.unos.PredefinisanaPosetaDTO;
import com.example.demo.dto.unos.ZahtevPosetaObradaDTO;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.repository.LekarRepository;
import com.example.demo.repository.PacijentRepository;
import com.example.demo.repository.SalaRepository;
import com.example.demo.repository.TipPoseteRepository;

@Component
public class PosetaConversion {
	
	@Autowired
	private TipPoseteRepository tipPoseteRepository;
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Autowired
	private LekarRepository lekarRepository;
	
	@Transactional(readOnly = true)
	public Poseta get(PredefinisanaPosetaDTO poseta) throws ParseException {
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		String temp1 = f.format(poseta.getDatum());
		String temp2 = temp1.substring(0, temp1.length() - 6);
		return new Poseta(f.parse(temp2 + " " + poseta.getVreme()), poseta.getPopust(), 
				StanjePosete.SLOBODNO, this.tipPoseteRepository.getOne(poseta.getTipPregleda()), 
				this.salaRepository.getOne(poseta.getSala()), 
				this.lekarRepository.getOne(poseta.getLekar()));

	}
	//kreiranje posete na osnovu zahteva!
	@Transactional(readOnly = true)
	public Poseta get(ZahtevPosetaObradaDTO poseta, SalaDTO salaDTO) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		Date pocetak = sdf.parse(poseta.getDatum());
		Date kraj = sdf.parse(poseta.getKraj());
		if (salaDTO.proveriDatum(pocetak, kraj)) {
			return new Poseta(this.pacijentRepository.getOne(poseta.getIdPacijent()).getKarton(),pocetak,null,StanjePosete.ZAUZETO, this.tipPoseteRepository.getOne(poseta.getIdTipa()),
					this.salaRepository.getOne(poseta.getIdSale()),
					this.lekarRepository.getOne(poseta.getIdLekar()));	
		}
		else {
			return null;
		}
		}
	
	public PosetaPretragaDTO get(Poseta poseta) {
		return new PosetaPretragaDTO(poseta);
	}
	
}
