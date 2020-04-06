package com.example.demo.dto;

import com.example.demo.model.Klinika;

public class KlinikaDTO {
	
	//treba prosiriti sa ostalim poljima klase Klinika, ali to cemo kasnije
	private Integer id;
	private String naziv;
	private String opis;
	
	public KlinikaDTO() {
		super();
	}

	public KlinikaDTO(Klinika klinika) {
		super();
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.opis = klinika.getOpis();
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

}
