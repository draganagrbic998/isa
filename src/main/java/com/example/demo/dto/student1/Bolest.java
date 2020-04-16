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
		
	private Integer id;
	private Integer klinikaId;
	private double ocena;
	private Date datum;
	private String klinika;
	private String tipPosete;
	private String nazivPosete;
	private String izvestaj;
	private List<LekarOcena> lekari;
	private List<DijagnozaDTO> dijagnoze;
	private List<Recept> recepti;
	
	public Bolest() {
		super();
	}

	public Bolest(Poseta poseta) {
		super();
		this.id = poseta.getId();
		this.klinikaId = poseta.getSala().getKlinika().getId();
		double suma = 0.0;
		int counter = 0;
		for (Ocena o: poseta.getSala().getKlinika().getOcene()) {
			suma += o.getVrednost();
			counter += 1;
		}
		this.ocena = counter != 0 ? suma / counter : 0.0;
		this.datum = poseta.getDatum();
		this.klinika = poseta.getSala().getKlinika().getNaziv();
		this.tipPosete = poseta.getTipPosete().getPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = poseta.getTipPosete().getNaziv();
		this.izvestaj = poseta.getIzvestaj().getOpis();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getKlinikaId() {
		return klinikaId;
	}

	public void setKlinikaId(Integer klinikaId) {
		this.klinikaId = klinikaId;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
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
