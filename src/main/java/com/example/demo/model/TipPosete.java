package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TipPosete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private Boolean pregled;
	@Column
	private String naziv;
	@Column
	private Double cena;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	
	@Column
	private Integer sati;
	
	@Column
	private Integer minuti;
	
	public TipPosete() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPregled() {
		return pregled;
	}

	public void setPregled(Boolean pregled) {
		this.pregled = pregled;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	public Integer getSati() {
		return sati;
	}

	public void setSati(Integer sati) {
		this.sati = sati;
	}

	public Integer getMinuti() {
		return minuti;
	}

	public void setMinuti(Integer minuti) {
		this.minuti = minuti;
	}
}
