package com.example.demo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GodisnjiDTO {

	private Integer id;
	private String pocetak;
	private String kraj;
	private Integer trajanje;
	private final SimpleDateFormat formatDatum = new SimpleDateFormat("yyyy-MM-dd");

	public GodisnjiDTO(Integer id, Date pocetak, Date kraj, Integer trajanje) {
		super();
		this.id = id;
		this.pocetak = formatDatum.format(pocetak);
		this.kraj = formatDatum.format(kraj);
		this.setTrajanje(trajanje);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

}
