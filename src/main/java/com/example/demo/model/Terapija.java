package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Terapija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	@JoinColumn(name="izvestaj")
	private Izvestaj izvestaj;
	@ManyToOne
	@JoinColumn(name="sestra")
	private Sestra sestra;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "terapija_lek",
    joinColumns = @JoinColumn(name = "terapija"),
    inverseJoinColumns = @JoinColumn(name = "lek"))
	private Set<Lek> lekovi = new HashSet<>();
	
	public Terapija() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Izvestaj getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(Izvestaj izvestaj) {
		this.izvestaj = izvestaj;
	}

	public Sestra getSestra() {
		return sestra;
	}

	public void setSestra(Sestra sestra) {
		this.sestra = sestra;
	}

	public Set<Lek> getLekovi() {
		return lekovi;
	}

	public void setLekovi(Set<Lek> lekovi) {
		this.lekovi = lekovi;
	}
	
	public Set<String> getDijagnozeSifre() {
		Set<String> sifre = new HashSet<>();
		
		for (Dijagnoza d : this.izvestaj.getDijagnoze()) {
			sifre.add(d.getSifra());
		}
		
		return sifre;
	}
	
	public Set<String> getLekoviSifre() {
		Set<String> sifre = new HashSet<>();
		
		for (Lek l : this.lekovi) {
			sifre.add(l.getSifra());
		}
		
		return sifre;
	}
}
