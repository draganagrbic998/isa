package com.example.demo.dto;

import com.example.demo.model.Izvestaj;

public class IzvestajDTO {
	
	private Integer id;
	private String opis;
	private Integer poseta;
	private Integer terapija;
	private String posetaNaziv;
	
	public IzvestajDTO() {
		super();
	}

	public IzvestajDTO(Izvestaj izvestaj) {
		super();
		this.id = izvestaj.getId();
		this.opis = izvestaj.getOpis();
		this.poseta = izvestaj.getPoseta().getId();
		this.posetaNaziv = izvestaj.getPoseta().getTipPosete().getNaziv();
		this.terapija = izvestaj.getTerapija().getId();
	}

	
	public String getPosetaNaziv() {
		return posetaNaziv;
	}

	public void setPosetaNaziv(String posetaNaziv) {
		this.posetaNaziv = posetaNaziv;
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

	public Integer getPoseta() {
		return poseta;
	}

	public void setPoseta(Integer poseta) {
		this.poseta = poseta;
	}

	public Integer getTerapija() {
		return terapija;
	}

	public void setTerapija(Integer terapija) {
		this.terapija = terapija;
	}
	
}
