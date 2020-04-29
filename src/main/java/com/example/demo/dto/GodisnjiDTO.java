package com.example.demo.dto;

import com.example.demo.model.ZahtevOdmor;

public class GodisnjiDTO extends ZahtevOdmorDTO{

	private int trajanje;

	public GodisnjiDTO() {
		super();
	}

	public GodisnjiDTO(ZahtevOdmor zahtev) {
		super(zahtev);
		this.trajanje = zahtev.getTrajanje();
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

}
