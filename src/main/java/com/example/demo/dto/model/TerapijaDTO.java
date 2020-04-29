package com.example.demo.dto.model;

import java.util.List;

import com.example.demo.model.Terapija;

public class TerapijaDTO implements Comparable<TerapijaDTO>{

	private Integer id;
	private String brOsiguranika;
	private List<String> dijagnoze;
	private List<String> lekovi;
	
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

	public List<String> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<String> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<String> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<String> lekovi) {
		this.lekovi = lekovi;
	}

	@Override
	public int compareTo(TerapijaDTO o) {
		return this.brOsiguranika.compareTo(o.brOsiguranika);
	}
	
}