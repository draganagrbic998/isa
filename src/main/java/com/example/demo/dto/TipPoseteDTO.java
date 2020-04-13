package com.example.demo.dto;

import com.example.demo.model.TipPosete;

public class TipPoseteDTO {
	private Integer id;
	private Boolean pregled;
	private String naziv;
	private Double cena;
	private Integer klinika;
	private Integer sati;
	private Integer minuti;
	
	public TipPoseteDTO() {
		super();
	}
	public TipPoseteDTO(TipPosete tip) {
		super();
		this.id = tip.getId();
		this.pregled = tip.getPregled();
		this.naziv = tip.getNaziv();
		this.cena = tip.getCena();
		this.klinika = tip.getKlinika().getId();
		this.sati = tip.getSati();
		this.minuti = tip.getMinuti();
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
	public Integer getMinuti() {
		return minuti;
	}
	public void setMinuti(Integer minuti) {
		this.minuti = minuti;
	}
	

}
