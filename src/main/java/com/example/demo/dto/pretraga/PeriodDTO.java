package com.example.demo.dto.pretraga;

import java.util.Date;

public class PeriodDTO {
	
	private Date pocetak;
	private Date kraj;
	
	public PeriodDTO() {
		super();
	}
	
	public PeriodDTO(Date pocetak, Date kraj) {
		super();
		this.pocetak = pocetak;
		this.kraj = kraj;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
	
}
