package com.example.demo.dto;

import com.example.demo.model.Lek;
import com.example.demo.model.Sestra;

public class Recept {
	
	private String sifra;
	private String naziv;
	private String sestra;
	
	public Recept() {
		super();
	}

	public Recept(Lek lek, Sestra sestra) {
		super();
		this.sifra = lek.getSifra();
		this.naziv = lek.getNaziv();
		this.sestra = sestra != null ? sestra.getIme() + " " + sestra.getPrezime() : "NEOVERENO";
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSestra() {
		return sestra;
	}

	public void setSestra(String sestra) {
		this.sestra = sestra;
	}

}
