package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.IzvestajDTO;
import com.example.demo.dto.IzvestajUnosDTO;
import com.example.demo.model.Izvestaj;
import com.example.demo.model.Lekar;
import com.example.demo.repository.DijagnozaRepository;
import com.example.demo.repository.PosetaRepository;
import com.example.demo.repository.TerapijaRepository;

@Component
public class IzvestajConversion {

	@Autowired
	TerapijaRepository terapijaRepository;

	@Autowired
	PosetaRepository posetaRepository;
	
	@Autowired
	DijagnozaRepository dijagnozaRepository;

	public Izvestaj get(IzvestajDTO izvestajDTO) {
		Izvestaj izvestaj = new Izvestaj();
		izvestaj.setId(izvestajDTO.getId());
		izvestaj.setOpis(izvestajDTO.getOpis());
		izvestaj.setPoseta(this.posetaRepository.getOne(izvestajDTO.getPoseta()));
		izvestaj.setTerapija(this.terapijaRepository.getOne(izvestajDTO.getTerapija()));
		return izvestaj;
	}

	public IzvestajDTO get(Izvestaj izvestaj) {
		return new IzvestajDTO(izvestaj);
	}

	public List<IzvestajDTO> get(List<Izvestaj> izvestaji) {
		List<IzvestajDTO> izvestajiDTO = new ArrayList<>();
		for (Izvestaj i : izvestaji) {
			izvestajiDTO.add(new IzvestajDTO(i));
		}
		return izvestajiDTO;
	}

	public Izvestaj get(IzvestajUnosDTO izvestajUnosDTO, Lekar lekar) {
		Izvestaj izvestaj = new Izvestaj();
		izvestaj.setId(izvestajUnosDTO.getId());
		izvestaj.setOpis(izvestajUnosDTO.getOpis());
		izvestaj.setPoseta(this.posetaRepository.getOne(lekar.getZapocetaPoseta().getId()));
		
		for (Integer lekId : izvestajUnosDTO.getDijagnoze())
			izvestaj.getDijagnoze().add(dijagnozaRepository.getOne(lekId));

		return izvestaj;
	}
}
