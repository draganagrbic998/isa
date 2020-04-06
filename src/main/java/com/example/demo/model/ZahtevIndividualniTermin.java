package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("individualan")
public class ZahtevIndividualniTermin extends ZahtevPoseta{
	
	@Column
	private Date pocetak;
	@ManyToOne
	@JoinColumn(name="tipPosete")
	private TipPosete tipPosete;
	@ManyToOne
	@JoinColumn(name="lekar")
	private Lekar lekar;
	
	public ZahtevIndividualniTermin() {
		super();
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public TipPosete getTipPosete() {
		return tipPosete;
	}

	public void setTipPosete(TipPosete tipPosete) {
		this.tipPosete = tipPosete;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

}
