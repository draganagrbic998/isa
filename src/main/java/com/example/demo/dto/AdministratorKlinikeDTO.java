package com.example.demo.dto;

import com.example.demo.model.Admin;

public class AdministratorKlinikeDTO {

	private Integer id;
	private String emailAdmin;
	private String lozinkaAdmin;
	private String imeAdmin;
	private String prezimeAdmin;
	private String telefonAdmin;
	private String novaDrzava;
	private String noviGrad;
	private String novaAdresa;
	private Integer novaKlinika;
	
	public AdministratorKlinikeDTO() {
		super();
	}

	public AdministratorKlinikeDTO(Admin admin) {
		super();
		this.id = admin.getId();
		this.emailAdmin = admin.getEmail();
		this.lozinkaAdmin = admin.getLozinka();
		this.imeAdmin = admin.getIme();
		this.prezimeAdmin = admin.getPrezime();
		this.telefonAdmin = admin.getTelefon();
		this.novaDrzava = admin.getLokacija().getDrzava();
		this.noviGrad = admin.getLokacija().getGrad();
		this.novaAdresa = admin.getLokacija().getAdresa();
		this.novaKlinika = admin.getKlinika().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmailAdmin() {
		return emailAdmin;
	}

	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}

	public String getLozinkaAdmin() {
		return lozinkaAdmin;
	}

	public void setLozinkaAdmin(String lozinkaAdmin) {
		this.lozinkaAdmin = lozinkaAdmin;
	}

	public String getImeAdmin() {
		return imeAdmin;
	}

	public void setImeAdmin(String imeAdmin) {
		this.imeAdmin = imeAdmin;
	}

	public String getPrezimeAdmin() {
		return prezimeAdmin;
	}

	public void setPrezimeAdmin(String prezimeAdmin) {
		this.prezimeAdmin = prezimeAdmin;
	}

	public String getTelefonAdmin() {
		return telefonAdmin;
	}

	public void setTelefonAdmin(String telefonAdmin) {
		this.telefonAdmin = telefonAdmin;
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

	public Integer getNovaKlinika() {
		return novaKlinika;
	}

	public void setNovaKlinika(Integer novaKlinika) {
		this.novaKlinika = novaKlinika;
	}

}

