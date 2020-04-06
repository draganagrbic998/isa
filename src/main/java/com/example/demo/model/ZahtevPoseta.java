package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public abstract class ZahtevPoseta extends ZahtevKlinika{

	@ManyToOne
	@JoinColumn(name="karton")
	private Karton karton;

	public ZahtevPoseta() {
		super();
	}

	public Karton getKarton() {
		return karton;
	}

	public void setKarton(Karton karton) {
		this.karton = karton;
	}
	
}
