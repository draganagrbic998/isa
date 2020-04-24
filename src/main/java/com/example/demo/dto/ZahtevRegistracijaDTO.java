package com.example.demo.dto;

import com.example.demo.model.ZahtevRegistracija;

public class ZahtevRegistracijaDTO implements Comparable<ZahtevRegistracijaDTO>{
	
	private Integer id;
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private String telefon;
	private String brojOsiguranika;
	private String drzava;
	private String grad;
	private String adresa;
	
	public ZahtevRegistracijaDTO() {
		super();
	}

	public ZahtevRegistracijaDTO(ZahtevRegistracija zahtev) {
		super();
		this.id = zahtev.getId();
		this.email = zahtev.getEmail();
		this.lozinka = zahtev.getLozinka();
		this.ime = zahtev.getIme();
		this.prezime = zahtev.getPrezime();
		this.telefon = zahtev.getTelefon();
		this.brojOsiguranika = zahtev.getBrojOsiguranika();
		this.drzava = zahtev.getDrzava();
		this.grad = zahtev.getGrad();
		this.adresa = zahtev.getAdresa();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getBrojOsiguranika() {
		return brojOsiguranika;
	}

	public void setBrojOsiguranika(String brojOsiguranika) {
		this.brojOsiguranika = brojOsiguranika;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Override
	public int compareTo(ZahtevRegistracijaDTO z) {
		return this.brojOsiguranika.compareTo(z.brojOsiguranika);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ZahtevRegistracijaDTO))
			return false;
		ZahtevRegistracijaDTO z = (ZahtevRegistracijaDTO) obj;
		return this.id.equals(z.id);
	}
	
}
