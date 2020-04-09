package com.example.demo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Lek;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;

public class Bolest {
		
	private Date datum;
	private String tipPosete;
	private String nazivPosete;
	private String izvestaj;
	private Set<String> lekari;
	private Set<DijagnozaDTO> dijagnoze;
	private Set<Recept> recepti;
	
	public Bolest() {
		super();
	}

	public Bolest(Poseta poseta) {
		super();
		this.datum = poseta.getPocetak();
		this.tipPosete = poseta.getTipPosete().getPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.izvestaj = poseta.getIzvestaj().getOpis();
		this.lekari = new HashSet<String>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(l.getIme() + " " + l.getPrezime());
		this.dijagnoze = new HashSet<DijagnozaDTO>();
		for (Dijagnoza d: poseta.getIzvestaj().getDijagnoze())
			this.dijagnoze.add(new DijagnozaDTO(d));
		this.recepti = new HashSet<Recept>();
		for (Lek l: poseta.getIzvestaj().getTerapija().getLekovi())
			this.recepti.add(new Recept(l, poseta.getIzvestaj().getTerapija().getSestra()));
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
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

	public String getIzvestaj() {
		return izvestaj;
	}

	public void setIzvestaj(String izvestaj) {
		this.izvestaj = izvestaj;
	}

	public Set<String> getLekari() {
		return lekari;
	}

	public void setLekari(Set<String> lekari) {
		this.lekari = lekari;
	}

	public Set<DijagnozaDTO> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(Set<DijagnozaDTO> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public Set<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(Set<Recept> recepti) {
		this.recepti = recepti;
	}

}
