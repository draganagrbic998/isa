package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ZahtevRegistracija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String noviEmail;
	@Column
	private String novaLozinka;
	@Column
	private String novoIme;
	@Column
	private String novoPrezime;
	@Column
	private String noviTelefon;
	@Column
	private String noviBrojOsiguranika;
	@ManyToOne
	@JoinColumn(name="lokacija")
	private Lokacija lokacija;
	
	public ZahtevRegistracija() {
		super();
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

	public Lokacija getLokacija() {
		return lokacija;
	}

	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}
	
}
