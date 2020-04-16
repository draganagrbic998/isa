package com.example.demo.dto;

import com.example.demo.model.Klinika;

public class KlinikaDTO {
	
	private Integer id;
	private String naziv;
	private String opis;
	private String adresa;
	
	public KlinikaDTO() {
		super();
	}

	public KlinikaDTO(Klinika klinika) {
		super();
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.opis = klinika.getOpis();
		this.adresa = klinika.getAdresa();
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

	
	
}
