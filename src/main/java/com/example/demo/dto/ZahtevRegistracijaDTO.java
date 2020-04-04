package com.example.demo.dto;

import com.example.demo.model.ZahtevRegistracija;

public class ZahtevRegistracijaDTO {
	
	private String emailDTO;
	private String lozinkaDTO;
	private String imeDTO;
	private String prezimeDTO;
	private String telefonDTO;
	private String brojOsiguranikaDTO;
	private String drzavaDTO;
	private String gradDTO;
	private String adresaDTO;
	
	public ZahtevRegistracijaDTO() {
		super();
	}

	public ZahtevRegistracijaDTO(ZahtevRegistracija zahtev) {
		super();
		this.emailDTO = zahtev.getNoviEmail();
		this.lozinkaDTO = zahtev.getNovaLozinka();
		this.imeDTO = zahtev.getNovoIme();
		this.prezimeDTO = zahtev.getNovoPrezime();
		this.telefonDTO = zahtev.getNoviTelefon();
		this.brojOsiguranikaDTO = zahtev.getNoviBrojOsiguranika();
		this.drzavaDTO = zahtev.getLokacija().getDrzava();
		this.gradDTO = zahtev.getLokacija().getGrad();
		this.adresaDTO = zahtev.getLokacija().getAdresa();
	}

	public String getEmailDTO() {
		return emailDTO;
	}

	public void setEmailDTO(String emailDTO) {
		this.emailDTO = emailDTO;
	}

	public String getLozinkaDTO() {
		return lozinkaDTO;
	}

	public void setLozinkaDTO(String lozinkaDTO) {
		this.lozinkaDTO = lozinkaDTO;
	}

	public String getImeDTO() {
		return imeDTO;
	}

	public void setImeDTO(String imeDTO) {
		this.imeDTO = imeDTO;
	}

	public String getPrezimeDTO() {
		return prezimeDTO;
	}

	public void setPrezimeDTO(String prezimeDTO) {
		this.prezimeDTO = prezimeDTO;
	}

	public String getTelefonDTO() {
		return telefonDTO;
	}

	public void setTelefonDTO(String telefonDTO) {
		this.telefonDTO = telefonDTO;
	}

	public String getBrojOsiguranikaDTO() {
		return brojOsiguranikaDTO;
	}

	public void setBrojOsiguranikaDTO(String brojOsiguranikaDTO) {
		this.brojOsiguranikaDTO = brojOsiguranikaDTO;
	}

	public String getDrzavaDTO() {
		return drzavaDTO;
	}

	public void setDrzavaDTO(String drzavaDTO) {
		this.drzavaDTO = drzavaDTO;
	}

	public String getGradDTO() {
		return gradDTO;
	}

	public void setGradDTO(String gradDTO) {
		this.gradDTO = gradDTO;
	}

	public String getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(String adresaDTO) {
		this.adresaDTO = adresaDTO;
	}

}
