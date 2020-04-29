package com.example.demo.dto.pretraga;

import com.example.demo.dto.model.LekarDTO;
import com.example.demo.model.Ocena;
import com.example.demo.model.korisnici.Lekar;

public class LekarOcenaDTO extends LekarDTO {
	
	private double ocena;

	public LekarOcenaDTO() {
		super();
	}

	public LekarOcenaDTO(Lekar lekar) {
		super(lekar);
		double suma = 0.0;
		int counter = 0;
		for (Ocena o: lekar.getOcene()) {
			suma += o.getVrednost();
			counter += 1;
		}
		this.ocena = counter != 0 ? suma / counter : 0.0;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

}
