package com.example.demo.dto;

import com.example.demo.model.Zaposleni;

public class ZaposleniDTO extends KorisnikDTO{
	
	private Integer klinika;
	
	public ZaposleniDTO() {
		super();
	}
	
	public ZaposleniDTO(Zaposleni zaposleni) {
		super(zaposleni);
		this.klinika = zaposleni.getKlinika().getId();
	}

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

}
