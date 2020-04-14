package com.example.demo.dto;

import com.example.demo.model.Dijagnoza;

public class DijagnozaDTO {

	private Integer id;
	private String sifra;
	private String naziv;
	
	public DijagnozaDTO() {
		super();
	}

	public DijagnozaDTO(Dijagnoza stavka) {
		super();
		this.id = stavka.getId();
		this.sifra = stavka.getSifra();
		this.naziv = stavka.getNaziv();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
}
