package com.example.demo.dto;

import java.util.Date;

public class PregledAdmin {
	
	private Integer id;
	private Date datum;
	private String vreme;
	private Integer sala;
	private Integer tip;
	private Integer lekar;
	private Double popust;
	
	public PregledAdmin(Integer id, Date datum, String vreme, Integer sala, Integer tip, Integer lekar, Double popust) {
		super();
		this.id = id;
		this.datum = datum;
		this.vreme = vreme;
		this.sala = sala;
		this.tip = tip;
		this.lekar = lekar;
		this.popust = popust;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tip) {
		this.tip = tip;
	}

	public Integer getLekar() {
		return lekar;
	}

	public void setLekar(Integer lekar) {
		this.lekar = lekar;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}
	
}
