package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;

public class Termin {
	
	private Integer id;		
	private Date datum;
	private String klinika;
	private String adresa;
	private Double originalnaCena;
	private Double popust;
	private Double novaCena;
	private String tipPosete;
	private String nazivPosete;
	private String sala;
	private List<String> lekari;		
	
	public Termin() {
		super();
	}

	public Termin(Poseta poseta) {
		super();
		this.id = poseta.getId();
		this.datum = poseta.getDatum();
		this.klinika = poseta.getTipPosete().getKlinika().getNaziv();
		this.adresa = poseta.getTipPosete().getKlinika().getAdresa();
		this.originalnaCena = poseta.getTipPosete().getCena();
		this.popust = poseta.getPopust();
		this.novaCena = this.popust != null ? this.originalnaCena - this.originalnaCena * this.popust : this.originalnaCena;
		this.tipPosete = poseta.getTipPosete().getPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.sala = poseta.getSala().getBroj() + " " + poseta.getSala().getNaziv();
		this.lekari = new ArrayList<String>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(l.getIme() + " " + l.getPrezime());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
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

	public List<String> getLekari() {
		return lekari;
	}

	public void setLekari(List<String> lekari) {
		this.lekari = lekari;
	}

}

