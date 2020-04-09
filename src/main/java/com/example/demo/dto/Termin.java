package com.example.demo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;

//ovo je trebala da bude klasa PosetaDTO
//medjutim, necemo stedeti na klasama
//ja sam napravila ovakvu klasu koja odgovara mojim zahtevima (meni ovakva struktura
//odgovara kad budem prikazivala pacijentu zakazane termine
//vama kada budete npr. rezervisali salu ili slicno, ovo nece biti dovoljno
//vi onda napravite novi klasu (nazovete npr. PosetaDTO) koja ce
//trebati da ima id sale (jer ce admin da postavlja taj id) meni je dovoljan naziv
//da ga prikazem pacijentu
public class Termin {
	
	private Integer id;		//treba jer cu mozda otkazati termin
	private Date pocetak;
	private String klinika;
	private String adresa;
	private Double originalnaCena;
	private Double popust;
	private Double novaCena;
	private String tipPosete;
	private String nazivPosete;
	private String sala;
	private Set<String> lekari;		//dovoljni mi da napisem npr. ime, prezime
	
	public Termin() {
		super();
	}

	public Termin(Poseta poseta) {
		super();
		this.id = poseta.getId();
		this.pocetak = poseta.getPocetak();
		this.klinika = poseta.getTipPosete().getKlinika().getNaziv();
		this.adresa = poseta.getTipPosete().getKlinika().getAdresa();
		this.originalnaCena = poseta.getTipPosete().getCena();
		this.popust = poseta.getPopust();
		this.novaCena = this.popust != null ? this.originalnaCena - this.originalnaCena * this.popust : this.originalnaCena;
		//ovo je ako je popust null, o null brinite i vi
		this.tipPosete = poseta.getTipPosete().getPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.sala = poseta.getSala().getBroj() + " " + poseta.getSala().getNaziv();
		this.lekari = new HashSet<String>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(l.getIme() + " " + l.getPrezime());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public String getKlinika() {
		return klinika;
	}

	public void setKlinika(String klinika) {
		this.klinika = klinika;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Double getCena() {
		return originalnaCena;
	}

	public void setCena(Double cena) {
		this.originalnaCena = cena;
	}

	public Double getPopust() {
		return popust;
	}

	public void setPopust(Double popust) {
		this.popust = popust;
	}

	public Double getOriginalnaCena() {
		return originalnaCena;
	}

	public void setOriginalnaCena(Double originalnaCena) {
		this.originalnaCena = originalnaCena;
	}

	public Double getNovaCena() {
		return novaCena;
	}

	public void setNovaCena(Double novaCena) {
		this.novaCena = novaCena;
	}

	public String getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(String tipPosete) {
		this.tipPosete = tipPosete;
	}

	public String getNazivPosete() {
		return nazivPosete;
	}

	public void setNazivPosete(String nazivPosete) {
		this.nazivPosete = nazivPosete;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public Set<String> getLekari() {
		return lekari;
	}

	public void setLekari(Set<String> lekari) {
		this.lekari = lekari;
	}

}

