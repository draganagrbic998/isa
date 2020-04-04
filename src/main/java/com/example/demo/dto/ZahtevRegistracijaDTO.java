package com.example.demo.dto;

public class ZahtevRegistracijaDTO {
	
	private String emailDTO;
	private String lozinkaDTO;
	private String imeDTO;
	private String prezimeDTO;
	private String telefonDTO;
	private String brojOsiguranikaDTO;
	
	public ZahtevRegistracijaDTO() {
		super();
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

}
