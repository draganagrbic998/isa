package com.example.demo.dto.pretraga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Hibernate;

import com.example.demo.dto.model.KlinikaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.model.resursi.Klinika;

public class KlinikaPretragaDTO extends KlinikaDTO{
	
	private double ocena;
	private double cena;
	private double trajanje;
	private List<LekarSatnicaDTO> lekari;
	
	public KlinikaPretragaDTO() {
		super();
	}

	public KlinikaPretragaDTO(Klinika klinika) {
		super(klinika);
		double suma = 0;
		int counter = 0;
		for (Ocena o: klinika.getOcene()) {
			suma += o.getVrednost();
			counter += 1;
		}
		this.ocena = counter != 0 ? suma / counter : 0.0;
		this.lekari = new ArrayList<>();
		for (Zaposleni z: klinika.getZaposleni()) {
			z = (Zaposleni) Hibernate.unproxy(z);
			if (z instanceof Lekar)
				this.lekari.add(new LekarSatnicaDTO((Lekar) z));
		}
		Collections.sort(this.lekari);
		
	}

	public KlinikaPretragaDTO(Klinika klinika, double cena, double trajanje) {
		this(klinika);
		this.cena = cena;
		this.trajanje = trajanje;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public double getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(double trajanje) {
		this.trajanje = trajanje;
	}

	public List<LekarSatnicaDTO> getLekari() {
		return lekari;
	}

	public void setLekari(List<LekarSatnicaDTO> lekari) {
		this.lekari = lekari;
	}

	public void dodaj(LekarSatnicaDTO ls) {
		this.lekari.add(ls);
	}
	
}