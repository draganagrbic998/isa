package com.example.demo.dto.pretraga;

import java.util.Date;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public class ZahtevTerminDTO implements Comparable<ZahtevTerminDTO>{
	
	private Integer id;
	private Date datum;
	private String tipPosete;
	private String nazivPosete;
	private String lekar;
	
	public ZahtevTerminDTO() {
		super();
	}
	public ZahtevTerminDTO(ZahtevPoseta zahtev) {
		super();
		this.id = zahtev.getId();
		this.datum = zahtev.getDatum();
		this.tipPosete = zahtev.getTipPosete().isPregled() ? "PREGLED" : "OPERACIJA";
		this.nazivPosete = zahtev.getTipPosete().getNaziv();
		this.lekar = zahtev.getLekar().getIme() + " " + zahtev.getLekar().getPrezime();
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
	public String getLekar() {
		return lekar;
	}
	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	@Override
	public int compareTo(ZahtevTerminDTO z) {
		return this.datum.compareTo(z.datum);
	}

}
