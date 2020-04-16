package com.example.demo.dto.student1;

import java.util.Date;

import com.example.demo.model.ZahtevPregled;

public class ZahtevPosetaDTO {
	
	private Integer id;
	private Date datum;
	private Integer lekar;
	
	public ZahtevPosetaDTO() {
		super();
	}

	public ZahtevPosetaDTO(ZahtevPregled zahtev) {
		super();
		this.id = zahtev.getId();
		this.datum = zahtev.getDatum();
		this.lekar = zahtev.getLekar().getId();
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

	public Integer getLekar() {
		return lekar;
	}

	public void setLekar(Integer lekar) {
		this.lekar = lekar;
	}

}