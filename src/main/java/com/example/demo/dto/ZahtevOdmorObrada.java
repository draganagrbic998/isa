package com.example.demo.dto;

import java.util.Date;

public class ZahtevOdmorObrada {
	
	private Integer id;
	private Integer zaposleniId;
	private String ime;
	private String prezime;
	private String profesija;
	private String pocetak;
	private String kraj;
	private String razlog;
	
	public ZahtevOdmorObrada(Integer id, String ime, String prezime, String profesija, String pocetak, String kraj, Integer zaposleniId, String razlog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.profesija = profesija;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.zaposleniId = zaposleniId;
		this.razlog = razlog;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getProfesija() {
		return profesija;
	}

	public void setProfesija(String profesija) {
		this.profesija = profesija;
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

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Integer getZaposleniId() {
		return zaposleniId;
	}

	public void setZaposleniId(Integer zaposleniId) {
		this.zaposleniId = zaposleniId;
	}

	public String getRazlog() {
		return razlog;
	}

	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}
}
