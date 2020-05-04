package com.example.demo.model.resursi;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.model.posete.Poseta;

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
	@Column
	private boolean aktivan;
	@OneToMany(mappedBy = "tipPosete", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();
	
	public TipPosete() {
		super();
	}

	public TipPosete(Integer id, boolean pregled, String naziv, int sati, int minute, double cena, Klinika klinika, boolean aktivan) {
		super();
		this.id = id;
		this.pregled = pregled;
		this.naziv = naziv;
		this.sati = sati;
		this.minute = minute;
		this.cena = cena;
		this.klinika = klinika;
		this.aktivan = aktivan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPregled() {
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

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Set<Poseta> getPosete() {
		return posete;
	}

	public void setPosete(Set<Poseta> posete) {
		this.posete = posete;
	}
	
}
