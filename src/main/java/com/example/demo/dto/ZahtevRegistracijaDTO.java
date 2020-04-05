package com.example.demo.dto;

import com.example.demo.model.ZahtevRegistracija;

public class ZahtevRegistracijaDTO {
	
	private Integer id;
	private String noviEmail;
	private String novaLozinka;
	private String novoIme;
	private String novoPrezime;
	private String noviTelefon;
	private String noviBrojOsiguranika;
	private String novaDrzava;
	private String noviGrad;
	private String novaAdresa;
	
	public ZahtevRegistracijaDTO() {
		super();
	}

	public ZahtevRegistracijaDTO(ZahtevRegistracija zahtev) {
		super();
		this.id = zahtev.getId();
		this.noviEmail = zahtev.getNoviEmail();
		this.novaLozinka = zahtev.getNovaLozinka();
		this.novoIme = zahtev.getNovoIme();
		this.novoPrezime = zahtev.getNovoPrezime();
		this.noviTelefon = zahtev.getNoviTelefon();
		this.noviBrojOsiguranika = zahtev.getNoviBrojOsiguranika();
		this.novaDrzava = zahtev.getNovaLokacija().getDrzava();
		this.noviGrad = zahtev.getNovaLokacija().getGrad();
		this.novaAdresa = zahtev.getNovaLokacija().getAdresa();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoviEmail() {
		return noviEmail;
	}

	public void setNoviEmail(String noviEmail) {
		this.noviEmail = noviEmail;
	}

	public String getNovaLozinka() {
		return novaLozinka;
	}

	public void setNovaLozinka(String novaLozinka) {
		this.novaLozinka = novaLozinka;
	}

	public String getNovoIme() {
		return novoIme;
	}

	public void setNovoIme(String novoIme) {
		this.novoIme = novoIme;
	}

	public String getNovoPrezime() {
		return novoPrezime;
	}

	public void setNovoPrezime(String novoPrezime) {
		this.novoPrezime = novoPrezime;
	}

	public String getNoviTelefon() {
		return noviTelefon;
	}

	public void setNoviTelefon(String noviTelefon) {
		this.noviTelefon = noviTelefon;
	}

	public String getNoviBrojOsiguranika() {
		return noviBrojOsiguranika;
	}

	public void setNoviBrojOsiguranika(String noviBrojOsiguranika) {
		this.noviBrojOsiguranika = noviBrojOsiguranika;
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

}
