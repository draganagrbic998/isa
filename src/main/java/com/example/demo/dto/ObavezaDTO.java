package com.example.demo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ObavezaDTO {

	private Integer id;
	private String datum;
	private String pocetak;
	private Integer trajanje;
	private String tip;
	private boolean pregled;
	private final SimpleDateFormat formatDatum = new SimpleDateFormat("yyyy-MM-dd");
	private final SimpleDateFormat formatPocetak = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public ObavezaDTO() {
		super();
	}

	public ObavezaDTO(Integer id, Date pocetak, int trajanje, String tip, boolean pregled) {
		this.id = id;
		this.datum = formatDatum.format(pocetak);
		this.pocetak = formatPocetak.format(pocetak);
		this.trajanje = trajanje;
		this.tip = tip;
		this.setPregled(pregled);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
	
	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

}
