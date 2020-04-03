package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Pacijent extends Korisnik{
	
	@OneToOne
	@JoinColumn(name="karton")
	private Karton karton;

	public Pacijent() {
		super();
	}

	public Karton getKarton() {
		return karton;
	}

	public void setKarton(Karton karton) {
		this.karton = karton;
	}

}
