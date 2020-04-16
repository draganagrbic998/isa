package com.example.demo.dto.student1;

import com.example.demo.dto.LekarDTO;
import com.example.demo.model.Lekar;
import com.example.demo.model.Ocena;

public class LekarOcena extends LekarDTO{
	
	private double ocena;

	public LekarOcena() {
		super();
	}

	public LekarOcena(Lekar lekar) {
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
