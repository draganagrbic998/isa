package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.DijagnozaDTO;
import com.example.demo.dto.LekarDTO;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Lek;
import com.example.demo.model.Lekar;
import com.example.demo.model.Poseta;

public class Bolest {
		
	private Integer id;
	private Date datum;
	private String klinika;
	private String tipPosete;
	private String nazivPosete;
	private String izvestaj;
	private List<LekarDTO> lekari;
	private List<DijagnozaDTO> dijagnoze;
	private List<Recept> recepti;
	
	public Bolest() {
		super();
	}

	public Bolest(Poseta poseta) {
		super();
		this.id = poseta.getSala().getKlinika().getId();
		this.datum = poseta.getDatum();
		this.klinika = poseta.getSala().getKlinika().getNaziv();
		this.tipPosete = poseta.getTipPosete().getPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.izvestaj = poseta.getIzvestaj().getOpis();
		this.lekari = new ArrayList<LekarDTO>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(new LekarDTO(l));
		this.dijagnoze = new ArrayList<DijagnozaDTO>();
		for (Dijagnoza d: poseta.getIzvestaj().getDijagnoze())
			this.dijagnoze.add(new DijagnozaDTO(d));
		this.recepti = new ArrayList<Recept>();
		for (Lek l: poseta.getIzvestaj().getTerapija().getLekovi())
			this.recepti.add(new Recept(l, poseta.getIzvestaj().getTerapija().getSestra()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKlinika() {
		return klinika;
	}

	public void setKlinika(String klinika) {
		this.klinika = klinika;
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

	public List<LekarDTO> getLekari() {
		return lekari;
	}

	public void setLekari(List<LekarDTO> lekari) {
		this.lekari = lekari;
	}

	public List<DijagnozaDTO> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<DijagnozaDTO> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<Recept> getRecepti() {
		return recepti;
	}

	public void setRecepti(List<Recept> recepti) {
		this.recepti = recepti;
	}

}
