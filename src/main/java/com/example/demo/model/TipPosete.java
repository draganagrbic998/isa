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
	private boolean pregled;
	@Column(unique = false, nullable = false)
	private String naziv;
	@Column(unique = false, nullable = false)
	private int sati;
	@Column(unique = false, nullable = false)
	private int minute;
	@Column(unique = false, nullable = false)
	private double cena;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	
	public TipPosete() {
		super();
	}

	public TipPosete(Integer id, boolean pregled, String naziv, int sati, int minute, double cena, Klinika klinika) {
		super();
		this.id = id;
		this.pregled = pregled;
		this.naziv = naziv;
		this.sati = sati;
		this.minute = minute;
		this.cena = cena;
		this.klinika = klinika;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public int getSati() {
		return sati;
	}

	public void setSati(int sati) {
		this.sati = sati;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}
	
}
