package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ZahtevPredefinisaniTermin extends ZahtevPoseta{

	@ManyToOne
	@JoinColumn(name="poseta")
	private Poseta poseta;

	public ZahtevPredefinisaniTermin() {
		super();
	}

	public Poseta getPoseta() {
		return poseta;
	}

	public void setPoseta(Poseta poseta) {
		this.poseta = poseta;
	}
	
}
