package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.example.demo.model.Klinika;
import com.example.demo.model.Lekar;
import com.example.demo.model.Ocena;
import com.example.demo.model.Zaposleni;

public class KlinikaPretraga {
	
	private Integer id;
	private String naziv;
	private String opis;
	private String adresa;
	private double ocena;
	private double cena;
	private double trajanje;
	private List<LekarSatnice> lekari;
	
	public KlinikaPretraga() {
		super();
	}

	public KlinikaPretraga(Klinika klinika) {
		super();
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.opis = klinika.getOpis();
		this.adresa = klinika.getAdresa();
		double suma = 0;
		int counter = 0;
		for (Ocena o: klinika.getOcene()) {
			suma += o.getVrednost();
			counter += 1;
		}
		this.ocena = counter != 0 ? suma / counter : 0;
		this.lekari = new ArrayList<>();
		for (Zaposleni z: klinika.getZaposleni()) {
			z = (Zaposleni) Hibernate.unproxy(z);
			if (z instanceof Lekar)
				this.lekari.add(new LekarSatnice((Lekar) z));
		}
		
	}

	public KlinikaPretraga(Klinika klinika, double cena, double trajanje) {
		this(klinika);
		this.cena = cena;
		this.trajanje = trajanje;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
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

	public List<LekarSatnice> getLekari() {
		return lekari;
	}

	public void setLekari(List<LekarSatnice> lekari) {
		this.lekari = lekari;
	}

	public void dodaj(LekarSatnice ls) {
		// TODO Auto-generated method stub
		this.lekari.add(ls);
	}
	
}
