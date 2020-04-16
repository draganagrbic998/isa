package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ZahtevPregled implements Zauzetost{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="karton")
	private Karton karton;
	@Column(unique = false, nullable = false)
	private Date datum;
	@ManyToOne
	@JoinColumn(name="lekar")
	private Lekar lekar;
	
	public ZahtevPregled() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Karton getKarton() {
		return karton;
	}

	public void setKarton(Karton karton) {
		this.karton = karton;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	@Override
	public int compareTo(Zauzetost o) {
		// TODO Auto-generated method stub
		return this.datum.compareTo(o.datum());
	}

	@Override
	public Date datum() {
		// TODO Auto-generated method stub
		return this.datum;
	}

	@Override
	public int sati() {
		// TODO Auto-generated method stub
		return this.lekar.getSpecijalizacija().getSati();
	}

	@Override
	public int minute() {
		// TODO Auto-generated method stub
		return this.lekar.getSpecijalizacija().getMinute();
	}
	
}
