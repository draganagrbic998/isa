package com.example.demo.dto.student1;

import com.example.demo.model.Lek;
import com.example.demo.model.Sestra;

public class ReceptDTO implements Comparable<ReceptDTO>{
	
	private String sifra;
	private String naziv;
	private String sestra;
	
	public ReceptDTO() {
		super();
	}

	public ReceptDTO(Lek lek, Sestra sestra) {
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

	@Override
	public int compareTo(ReceptDTO r) {
		return this.sifra.compareTo(r.sifra);
	}
	
}
