package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.DijagnozaDTO;
import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Lek;
import com.example.demo.model.Lekar;
import com.example.demo.model.Ocena;
import com.example.demo.model.Poseta;

public class Bolest {
		
	private Integer posetaId;
	private Integer klinikaId;
	private Date datum;
	private String klinika;
	private String tipPosete;
	private String nazivPosete;
	private String izvestaj;
	private double ocenaKlinike;
	private List<LekarOcena> lekari;
	private List<DijagnozaDTO> dijagnoze;
	private List<Recept> recepti;
	
	public Bolest() {
		super();
	}

	public Bolest(Poseta poseta) {
		super();
		this.posetaId = poseta.getId();
		this.klinikaId = poseta.getSala().getKlinika().getId();
		this.datum = poseta.getDatum();
		this.klinika = poseta.getSala().getKlinika().getNaziv();
		this.tipPosete = poseta.getTipPosete().getPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.izvestaj = poseta.getIzvestaj().getOpis();
		double suma = 0.0;
		int counter = 0;
		for (Ocena o: poseta.getSala().getKlinika().getOcene()) {
			suma += o.getVrednost();
			counter += 1;
		}
		this.ocenaKlinike = counter != 0 ? suma / counter : 0.0;
		this.lekari = new ArrayList<>();
		for (Lekar l: poseta.getLekari())
			this.lekari.add(new LekarOcena(l));
		this.dijagnoze = new ArrayList<>();
		for (Dijagnoza d: poseta.getIzvestaj().getDijagnoze())
			this.dijagnoze.add(new DijagnozaDTO(d));
		this.recepti = new ArrayList<>();
		for (Lek l: poseta.getIzvestaj().getTerapija().getLekovi())
			this.recepti.add(new Recept(l, poseta.getIzvestaj().getTerapija().getSestra()));
	}

	public Integer getPosetaId() {
		return posetaId;
	}

	public void setPosetaId(Integer posetaId) {
		this.posetaId = posetaId;
	}

	public Integer getKlinikaId() {
		return klinikaId;
	}

	public void setKlinikaId(Integer klinikaId) {
		this.klinikaId = klinikaId;
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

	public double getOcenaKlinike() {
		return ocenaKlinike;
	}

	public void setOcenaKlinike(double ocenaKlinike) {
		this.ocenaKlinike = ocenaKlinike;
	}

	public List<LekarOcena> getLekari() {
		return lekari;
	}

	public void setLekari(List<LekarOcena> lekari) {
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
