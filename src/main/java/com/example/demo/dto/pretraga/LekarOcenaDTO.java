package com.example.demo.dto.pretraga;

import com.example.demo.dto.model.LekarDTO;
import com.example.demo.model.korisnici.Lekar;

public class LekarOcenaDTO extends LekarDTO {
	
	private double ocena;

	public LekarOcenaDTO() {
		super();
	}

	public LekarOcenaDTO(Lekar lekar) {
		super(lekar);
		this.ocena = lekar.prosecnaOcena();
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

}
