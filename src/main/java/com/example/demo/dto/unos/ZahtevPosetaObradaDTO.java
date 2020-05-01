package com.example.demo.dto.unos;

import java.util.Date;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public class ZahtevPosetaObradaDTO {
	
	private Integer id;
	private String lekar;
	private String pacijent;
	private boolean pregled;
	private String naziv;
	private Date datum;
	
	public ZahtevPosetaObradaDTO() {
		super();
	}
	
	public ZahtevPosetaObradaDTO(ZahtevPoseta zahtev) {
		this.id = zahtev.getId();
		this.datum = zahtev.getDatum();
		this.pacijent = zahtev.getKarton().getPacijent().getIme()+" "+zahtev.getKarton().getPacijent().getPrezime();
		this.lekar = zahtev.getLekar().getIme()+" "+zahtev.getLekar().getPrezime();
		this.naziv = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getNaziv() : null;
		this.pregled = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getPregled() : null;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String tipPosete) {
		this.naziv = tipPosete;
	}
	
	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
