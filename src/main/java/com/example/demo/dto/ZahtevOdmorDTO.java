package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.ZahtevOdmor;

public class ZahtevOdmorDTO implements Comparable<ZahtevOdmorDTO>{

	private Integer id;
	private Date pocetak;
	private Date kraj;
	private boolean odobren;
	private Integer zaposleni;
	private Integer klinika;
	
	public ZahtevOdmorDTO() {
		super();
	}
	
	public ZahtevOdmorDTO(ZahtevOdmor zahtev) {
		super();
		this.id = zahtev.getId();
		this.pocetak = zahtev.getPocetak();
		this.kraj = zahtev.getKraj();
		this.odobren = zahtev.getOdobren();
		this.zaposleni = zahtev.getZaposleni().getId();
		this.klinika = zahtev.getKlinika().getId();
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

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}
	

	public Integer getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Integer zaposleni) {
		this.zaposleni = zaposleni;
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	@Override
	public int compareTo(ZahtevOdmorDTO z) {
		return this.id.compareTo(z.id);
	}

}
