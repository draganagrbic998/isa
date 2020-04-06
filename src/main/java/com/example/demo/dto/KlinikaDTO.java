package com.example.demo.dto;

import com.example.demo.model.Klinika;

public class KlinikaDTO {
	
	//treba prosiriti sa ostalim poljima klase Klinika, ali to cemo kasnije
	private Integer id;
	private String naziv;
	private String opis;
	private String novaDrzava;
	private String noviGrad;
	private String novaAdresa;
	
	public KlinikaDTO() {
		super();
	}

	public KlinikaDTO(Klinika klinika) {
		super();
		this.id = klinika.getId();
		this.naziv = klinika.getNaziv();
		this.opis = klinika.getOpis();
		this.novaAdresa = klinika.getLokacija().getAdresa();
		this.novaDrzava = klinika.getLokacija().getDrzava();
		this.noviGrad = klinika.getLokacija().getGrad();
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

	public String getNovaDrzava() {
		return novaDrzava;
	}

	public void setNovaDrzava(String novaDrzava) {
		this.novaDrzava = novaDrzava;
	}

	public String getNoviGrad() {
		return noviGrad;
	}

	public void setNoviGrad(String noviGrad) {
		this.noviGrad = noviGrad;
	}

	public String getNovaAdresa() {
		return novaAdresa;
	}

	public void setNovaAdresa(String novaAdresa) {
		this.novaAdresa = novaAdresa;
	}

}
