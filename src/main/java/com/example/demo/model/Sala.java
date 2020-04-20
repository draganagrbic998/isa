package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String broj;
	@Column(unique = false, nullable = false)
	private String naziv;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	@Column(unique = false, nullable = false)
	private boolean aktivan;
	
	public Sala() {
		super();
	}

	public Sala(Integer id, String broj, String naziv, Klinika klinika, boolean aktivan) {
		super();
		this.id = id;
		this.broj = broj;
		this.naziv = naziv;
		this.klinika = klinika;
		this.aktivan = aktivan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
}
