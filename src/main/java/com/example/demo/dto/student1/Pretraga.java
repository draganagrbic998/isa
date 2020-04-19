package com.example.demo.dto.student1;

import java.util.Date;

public class Pretraga {
	
	private String tipPregleda;
	private Date datumPregleda;
	
	public Pretraga() {
		super();
	}

	public String getTipPregleda() {
		return tipPregleda;
	}

	public void setTipPregleda(String tipPregleda) {
		this.tipPregleda = tipPregleda;
	}

	public Date getDatumPregleda() {
		return datumPregleda;
	}

	public void setDatumPregleda(Date datumPregleda) {
		this.datumPregleda = datumPregleda;
	}

}
