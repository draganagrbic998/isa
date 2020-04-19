package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.model.Klinika;
import com.example.demo.model.Lekar;
import com.example.demo.model.Ocena;
import com.example.demo.model.Zaposleni;

public class KlinikaPretraga extends KlinikaDTO{
	
	private double ocena;
	private double cena;
	private double trajanje;
	private List<LekarSatnica> lekari;
	
	public KlinikaPretraga() {
		super();
	}

	public KlinikaPretraga(Klinika klinika) {
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
				this.lekari.add(new LekarSatnica((Lekar) z));
		}
		
	}

	public KlinikaPretraga(Klinika klinika, double cena, double trajanje) {
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

	public List<LekarSatnica> getLekari() {
		return lekari;
	}

	public void setLekari(List<LekarSatnica> lekari) {
		this.lekari = lekari;
	}

	public void dodaj(LekarSatnica ls) {
		this.lekari.add(ls);
	}
	
}
