package com.example.demo.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.unos.IzvestajUnosDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Izvestaj;
import com.example.demo.repository.DijagnozaRepository;
import com.example.demo.repository.PosetaRepository;

@Component
public class IzvestajConversion {

	@Autowired
	private PosetaRepository posetaRepository;
	
	@Autowired
	private DijagnozaRepository dijagnozaRepository;
	
	public Izvestaj get(IzvestajUnosDTO izvestajUnosDTO, Lekar lekar) {
		Izvestaj izvestaj = new Izvestaj();
		izvestaj.setId(izvestajUnosDTO.getId());
		izvestaj.setOpis(izvestajUnosDTO.getOpis());
		izvestaj.setPoseta(this.posetaRepository.getOne(lekar.getZapocetaPoseta().getId()));
		
		for (Integer id : izvestajUnosDTO.getDijagnoze())
			izvestaj.getDijagnoze().add(dijagnozaRepository.getOne(id));

		return izvestaj;
	}
	
}
