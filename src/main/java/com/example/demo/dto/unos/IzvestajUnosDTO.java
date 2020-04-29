package com.example.demo.dto.unos;

import java.util.List;

public class IzvestajUnosDTO {

	private Integer id;
	private String opis;
	private List<Integer> dijagnoze;
	private List<Integer> lekovi;

	public IzvestajUnosDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Integer> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<Integer> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<Integer> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Integer> lekovi) {
		this.lekovi = lekovi;
	}

}
