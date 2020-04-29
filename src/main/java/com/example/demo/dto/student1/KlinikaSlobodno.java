package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.Ocena;
import com.example.demo.model.Poseta;
import com.example.demo.model.StanjePosete;

public class KlinikaSlobodno extends KlinikaDTO {
	
	private double ocena;
	private List<PosetaPretraga> posete;
	
	public KlinikaSlobodno() {
		super();
	}

	public KlinikaSlobodno(Klinika klinika, List<Poseta> posete) {
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
				this.posete.add(new PosetaPretraga(p));
		}
		Collections.sort(this.posete);
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public List<PosetaPretraga> getPosete() {
		return posete;
	}

	public void setPosete(List<PosetaPretraga> posete) {
		this.posete = posete;
	}

}
