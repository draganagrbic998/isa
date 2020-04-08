package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("lekar")
public class Lekar extends Zaposleni{

	@Column
	private String specijalizacija;
	@OneToMany
	private Set<Ocena> ocene;
	@ManyToMany
	private Set<Poseta> posete;
	@OneToMany
	private Set<ZahtevOdsustvo> zahtevi;
	
	public Lekar() {
		super();
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Poseta> getPosete() {
		return posete;
	}

	public void setPosete(Set<Poseta> posete) {
		this.posete = posete;
	}

	public Set<ZahtevOdsustvo> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(Set<ZahtevOdsustvo> zahtevi) {
		this.zahtevi = zahtevi;
	}
	
}
