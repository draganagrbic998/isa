package com.example.demo.dto.model;

import com.example.demo.model.korisnici.Lekar;

public class LekarDTO extends ZaposleniDTO {

	private Integer specijalizacija;
	private Double prosecnaOcena;
	
	public LekarDTO() {
		super();
	}
	
	public LekarDTO(Lekar lekar) {
		super(lekar);
		this.specijalizacija = lekar.getSpecijalizacija().getId();
		this.prosecnaOcena = lekar.prosecnaOcena();
	}

	
	public Double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public void setProsecnaOcena(Double ocena) {
		this.prosecnaOcena = ocena;
	}

	public Integer getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(Integer specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

}
