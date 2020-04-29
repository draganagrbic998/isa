package com.example.demo.dto.student1;

import java.util.Date;

public class PretragaDTO {
	
	private String tipPregleda;
	private Date datumPregleda;
	
	public PretragaDTO() {
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
