package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("lekar")
public class Lekar extends Zaposleni implements Ocenjivanje{

	@ManyToOne
	@JoinColumn(name="specijalizacija")
	private TipPosete specijalizacija;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_ocena",
    joinColumns = @JoinColumn(name = "lekar"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_poseta",
    joinColumns = @JoinColumn(name = "lekar"),
    inverseJoinColumns = @JoinColumn(name = "poseta"))
	private Set<Poseta> posete = new HashSet<>();
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<ZahtevOdmor> odmorZahtevi = new HashSet<>();
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<ZahtevPregled> pregledZahtevi = new HashSet<>();
	
	public Lekar() {
		super();
	}

	public TipPosete getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(TipPosete specijalizacija) {
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

	public Set<ZahtevOdmor> getOdmorZahtevi() {
		return odmorZahtevi;
	}

	public void setOdmorZahtevi(Set<ZahtevOdmor> odmorZahtevi) {
		this.odmorZahtevi = odmorZahtevi;
	}

	public Set<ZahtevPregled> getPregledZahtevi() {
		return pregledZahtevi;
	}

	public void setPregledZahtevi(Set<ZahtevPregled> pregledZahtevi) {
		this.pregledZahtevi = pregledZahtevi;
	}

	@Override
	public Ocena refreshOcena(Pacijent pacijent, int ocena) {

		for (Ocena o: this.ocene) {
			if (o.getPacijent().getId().equals(pacijent.getId())) {
				o.setVrednost(ocena);
				return o;
			}
		}
		Ocena o = new Ocena(pacijent, ocena);
		this.ocene.add(o);
		return o;
		
	}
	
}