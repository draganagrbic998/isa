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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.example.demo.model.Ocena;
import com.example.demo.model.Ocenjivanje;
import com.example.demo.model.korisnici.Pacijent;
import com.example.demo.model.korisnici.Zaposleni;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.model.zahtevi.ZahtevPoseta;

@Entity
public class Klinika implements Ocenjivanje{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String naziv;
	@Column(unique = false, nullable = true)
	private String opis;
	@Column(unique = false, nullable = false)
	private String adresa;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "klinika_ocena",
    joinColumns = @JoinColumn(name = "klinika"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<Zaposleni> zaposleni = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<TipPosete> tipoviPoseta = new HashSet<>();
	@OneToMany(mappedBy = "klinika", fetch = FetchType.EAGER)
	private Set<Sala> sale = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "klinika_pregled",
    joinColumns = @JoinColumn(name = "klinika"),
    inverseJoinColumns = @JoinColumn(name = "pregled"))
	private Set<ZahtevPoseta> posetaZahtevi = new HashSet<>();
	@OneToMany(fetch = FetchType.EAGER, mappedBy="klinika")
	private Set<ZahtevOdmor> odmorZahtevi = new HashSet<>();
	
	public Klinika() {
		super();
	}

	public Klinika(Integer id, String naziv, String opis, String adresa) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.adresa = adresa;
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

	public Set<ZahtevPoseta> getPosetaZahtevi() {
		return posetaZahtevi;
	}

	public void setPosetaZahtevi(Set<ZahtevPoseta> posetaZahtevi) {
		this.posetaZahtevi = posetaZahtevi;
	}

	public Set<ZahtevOdmor> getOdmorZahtevi() {
		return odmorZahtevi;
	}

	public void setOdmorZahtevi(Set<ZahtevOdmor> odmorZahtevi) {
		this.odmorZahtevi = odmorZahtevi;
	}

	@Override
	public Ocena refreshOcena(Pacijent pacijent, int ocena) {


		for (Ocena o: this.ocene) {
			if (o.getPacijent().getId().equals(pacijent.getId())) {
				o.setVrednost(ocena);
				return o;
			}
		}
		Ocena o = new Ocena(pacijent, ocena, this.id + "K");
		this.ocene.add(o);
		return o;
	}
	
}
