package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Zaposleni extends Korisnik{
	
	@Column
	private Date pocetnoVreme;
	@Column
	private Date krajnjeVreme;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	
	public Zaposleni() {
		super();
	}

	public Date getPocetnoVreme() {
		return pocetnoVreme;
	}

	public void setPocetnoVreme(Date pocetnoVreme) {
		this.pocetnoVreme = pocetnoVreme;
	}

	public Date getKrajnjeVreme() {
		return krajnjeVreme;
	}

	public void setKrajnjeVreme(Date krajnjeVreme) {
		this.krajnjeVreme = krajnjeVreme;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

}
