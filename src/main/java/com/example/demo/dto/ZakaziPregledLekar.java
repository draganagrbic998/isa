package com.example.demo.dto;

import java.util.Date;

public class ZakaziPregledLekar {
	
	private Integer id;
	private Date datum;
	private String vreme;
	private Integer lekar;
	private Integer karton;
	private Integer tip;
	
	

	public ZakaziPregledLekar(Integer id, Date datum, String vreme, Integer lekar, Integer karton, Integer tipPosete) {
		super();
		this.id = id;
		this.datum = datum;
		this.vreme = vreme;
		this.karton = karton;
		this.lekar = lekar;
		this.tip = tipPosete;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKarton() {
		return karton;
	}

	public void setKarton(Integer karton) {
		this.karton = karton;
	}

	public Integer getLekar() {
		return lekar;
	}

	public void setLekar(Integer lekar) {
		this.lekar = lekar;
	}

	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tipPosete) {
		this.tip = tipPosete;
	}
	
	
}
