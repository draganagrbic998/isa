package com.example.demo.dto;

import java.util.Date;

import com.example.demo.model.ZahtevPoseta;

public class ZahtevPosetaDTO implements Comparable<ZahtevPosetaDTO>{
	
	private Integer id;
	private Date datum;
	private Integer karton;
	private Integer lekar;
	private Integer tipPosete;
	
	public ZahtevPosetaDTO() {
		super();
	}

	public ZahtevPosetaDTO(ZahtevPoseta zahtev) {
		super();
		this.id = zahtev.getId();
		this.datum = zahtev.getDatum();
		this.karton = zahtev.getKarton().getId();
		this.lekar = zahtev.getLekar().getId();
		this.tipPosete = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getId() : null;
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

	public Integer getKarton() {
		return karton;
	}

	public void setKarton(Integer karton) {
		this.karton = karton;
	}

	public Integer getLekar() {
		return lekar;
	}

	public void setLekar(Integer lekar) {
		this.lekar = lekar;
	}

	public Integer getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(Integer tipPosete) {
		this.tipPosete = tipPosete;
	}

	@Override
	public int compareTo(ZahtevPosetaDTO z) {
		return this.datum.compareTo(z.datum);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ZahtevPosetaDTO))
			return false;
		ZahtevPosetaDTO z = (ZahtevPosetaDTO) obj;
		return this.id.equals(z.id);
	}
	
}