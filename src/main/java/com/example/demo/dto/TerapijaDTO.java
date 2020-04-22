package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Terapija;

public class TerapijaDTO {

	private Integer id;
	private String brOsiguranika;
	private Set<String> dijagnoze;
	private Set<String> lekovi;
	
	public TerapijaDTO() {
		super();
	}

	public TerapijaDTO(Terapija terapija) {
		super();
		this.setId(terapija.getId());
		this.setBrOsiguranika(terapija.getIzvestaj().getPoseta().getKarton().getBrojOsiguranika());
		this.setDijagnoze(terapija.getDijagnozeSifre());
		this.setLekovi(terapija.getLekoviSifre());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrOsiguranika() {
		return brOsiguranika;
	}

	public void setBrOsiguranika(String brOsiguranika) {
		this.brOsiguranika = brOsiguranika;
	}

	public Set<String> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<String> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Set<String> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<String> lekovi) {
		this.lekovi = lekovi;
	}
	
}