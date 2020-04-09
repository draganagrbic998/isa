package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Klinika {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String naziv;
	@Column
	private String opis;
	@Column
	private String adresa;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "klinika_ocena",
    joinColumns = @JoinColumn(name = "klinika"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene;
	//ovo pravljenje nove tabele moramo jer ocena nema referencu na kliniku
	//glavni razlog sto ocena nema referencu na kliniku je sto ona moze da se odnosi i na lekara
	//a lekar i klinika ne implementiraju neki zajednicki interfejs
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<Zaposleni> zaposleni;
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<TipPosete> tipoviPoseta;
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<Sala> sale;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "klinika_zahtev",
    joinColumns = @JoinColumn(name = "klinika"),
    inverseJoinColumns = @JoinColumn(name = "zahtev"))
	private Set<ZahtevKlinika> zahtevi;
	//ovo pravljenje nove tabele moramo jer zahtev nema referencu na kliniku
	
	public Klinika() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Set<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Set<TipPosete> getTipoviPoseta() {
		return tipoviPoseta;
	}

	public void setTipoviPoseta(Set<TipPosete> tipoviPoseta) {
		this.tipoviPoseta = tipoviPoseta;
	}

	public Set<Sala> getSale() {
		return sale;
	}

	public void setSale(Set<Sala> sale) {
		this.sale = sale;
	}

	public Set<ZahtevKlinika> getZahtevi() {
		return zahtevi;
	}

	public void setZahtevi(Set<ZahtevKlinika> zahtevi) {
		this.zahtevi = zahtevi;
	}
	
}
