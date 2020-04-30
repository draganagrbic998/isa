package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.model.KlinikaDTO;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Klinika;

public class KlinikaSlobodnoDTO extends KlinikaDTO {
	
	private double ocena;
	private List<PosetaPretragaDTO> posete;
	
	public KlinikaSlobodnoDTO() {
		super();
	}

	public KlinikaSlobodnoDTO(Klinika klinika, List<Poseta> posete) {
		super(klinika);
		double suma = 0;
		int counter = 0;
		for (Ocena o: klinika.getOcene()) {
			suma += o.getVrednost();
			counter += 1;
		}
		this.ocena = counter != 0 ? suma / counter : 0.0;
		this.posete = new ArrayList<>();
		for (Poseta p: posete) {
			if (p.getTipPosete().getPregled() && p.getStanje().equals(StanjePosete.SLOBODNO) && p.getDatum().after(new Date()))
				this.posete.add(new PosetaPretragaDTO(p));
		}
		Collections.sort(this.posete);
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public List<PosetaPretragaDTO> getPosete() {
		return posete;
	}

	public void setPosete(List<PosetaPretragaDTO> posete) {
		this.posete = posete;
	}

}
