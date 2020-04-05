package com.example.demo.dto;

import com.example.demo.model.Lekar;

public class LekarDTO {

	private String emailLekar;
	private String lozinkaLekar;
	private String imeLekar;
	private String prezimeLekar;
	private String telefonLekar;
	private String novaDrzava;
	private String noviGrad;
	private String novaAdresa;
	private String novaSpecijalizacija;
	private Integer novaKlinika;
	
	public LekarDTO() {
		super();
	}

	public LekarDTO(Lekar lekar) {
		super();
		this.emailLekar = lekar.getEmail();
		this.lozinkaLekar = lekar.getLozinka();
		this.imeLekar = lekar.getIme();
		this.prezimeLekar = lekar.getPrezime();
		this.telefonLekar = lekar.getTelefon();
		this.novaSpecijalizacija = lekar.getSpecijalizacija();
		this.novaDrzava = lekar.getLokacija().getDrzava();
		this.noviGrad = lekar.getLokacija().getGrad();
		this.novaAdresa = lekar.getLokacija().getAdresa();
		this.novaKlinika = lekar.getKlinika().getId();
	}

	public String getEmailLekar() {
		return emailLekar;
	}

	public void setEmailLekar(String emailLekar) {
		this.emailLekar = emailLekar;
	}

	public String getLozinkaLekar() {
		return lozinkaLekar;
	}

	public void setLozinkaLekar(String lozinkaLekar) {
		this.lozinkaLekar = lozinkaLekar;
	}

	public String getImeLekar() {
		return imeLekar;
	}

	public void setImeLekar(String imeLekar) {
		this.imeLekar = imeLekar;
	}

	public String getPrezimeLekar() {
		return prezimeLekar;
	}

	public void setPrezimeLekar(String prezimeLekar) {
		this.prezimeLekar = prezimeLekar;
	}

	public String getTelefonLekar() {
		return telefonLekar;
	}

	public void setTelefonLekar(String telefonLekar) {
		this.telefonLekar = telefonLekar;
	}

	public String getNovaDrzava() {
		return novaDrzava;
	}

	public void setNovaDrzava(String novaDrzava) {
		this.novaDrzava = novaDrzava;
	}

	public String getNoviGrad() {
		return noviGrad;
	}

	public void setNoviGrad(String noviGrad) {
		this.noviGrad = noviGrad;
	}

	public String getNovaAdresa() {
		return novaAdresa;
	}

	public void setNovaAdresa(String novaAdresa) {
		this.novaAdresa = novaAdresa;
	}

	public String getNovaSpecijalizacija() {
		return novaSpecijalizacija;
	}

	public void setNovaSpecijalizacija(String novaSpecijalizacija) {
		this.novaSpecijalizacija = novaSpecijalizacija;
	}

	public Integer getNovaKlinika() {
		return novaKlinika;
	}

	public void setNovaKlinika(Integer novaKlinika) {
		this.novaKlinika = novaKlinika;
	}

}

