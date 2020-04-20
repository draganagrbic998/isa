package com.example.demo.dto;

import com.example.demo.model.Sala;

public class SalaDTO {

	private Integer id; 
	private String broj;
	private String naziv;
	private Integer klinika;
	private boolean aktivan;
	
	public SalaDTO() {
		super();
	}
	
	public SalaDTO(Sala sala) {
		this.id = sala.getId();
		this.broj = sala.getBroj();
		this.naziv = sala.getNaziv();
		this.klinika = sala.getKlinika().getId();
		this.aktivan = sala.isAktivan();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
}
