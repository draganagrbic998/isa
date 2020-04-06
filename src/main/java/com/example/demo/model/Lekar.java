package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("lekar")
public class Lekar extends Zaposleni{

	@Column
	private String specijalizacija;
	@OneToOne
	@JoinColumn(name="ocena")
	private Ocena ocena;
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

	public Ocena getOcena() {
		return ocena;
	}

	public void setOcena(Ocena ocena) {
		this.ocena = ocena;
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
