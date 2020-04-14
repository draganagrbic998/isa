package com.example.demo.dto;

import com.example.demo.model.TipPosete;

public class TipPoseteDTO {
	
	private Integer id;
	private Boolean pregled;
	private String naziv;
	private Double cena;
	private Integer klinika;
	private Integer sati;
	private Integer minute;
	
	public TipPoseteDTO() {
		super();
	}

	public TipPoseteDTO(TipPosete tipPosete) {
		super();
		this.id = tipPosete.getId();
		this.pregled = tipPosete.getPregled();
		this.naziv = tipPosete.getNaziv();
		this.cena = tipPosete.getCena();
		this.klinika = tipPosete.getKlinika().getId();
		this.sati = tipPosete.getSati();
		this.minute = tipPosete.getMinute();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPregled() {
		return pregled;
	}

	public void setPregled(Boolean pregled) {
		this.pregled = pregled;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	public Integer getSati() {
		return sati;
	}

	public void setSati(Integer sati) {
		this.sati = sati;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

}
