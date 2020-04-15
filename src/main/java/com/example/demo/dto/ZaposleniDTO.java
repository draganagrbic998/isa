package com.example.demo.dto;

import java.text.SimpleDateFormat;

import com.example.demo.model.Zaposleni;

public class ZaposleniDTO extends KorisnikDTO{
	
	
	public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
	
	private String pocetnoVreme;
	private String krajnjeVreme;
	
	private Integer klinika;
	
	public ZaposleniDTO() {
		super();
	}
	
	public ZaposleniDTO(Zaposleni zaposleni) {
		super(zaposleni);
		String pocetno = this.format.format(zaposleni.getPocetnoVreme());
		String krajnje = this.format.format(zaposleni.getKrajnjeVreme());
		this.klinika = zaposleni.getKlinika().getId();
		this.pocetnoVreme = pocetno.substring(pocetno.length() - 5);
		this.krajnjeVreme = krajnje.substring(krajnje.length() - 5);
	}

	public String getPocetnoVreme() {
		return pocetnoVreme;
	}

	public void setPocetnoVreme(String pocetnoVreme) {
		this.pocetnoVreme = pocetnoVreme;
	}

	public String getKrajnjeVreme() {
		return krajnjeVreme;
	}

	public void setKrajnjeVreme(String krajnjeVreme) {
		this.krajnjeVreme = krajnjeVreme;
	}
	
	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

}
