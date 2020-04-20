package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"pacijent", "provera"})})
public class Ocena {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;
	@Column(unique = false, nullable = false)
	private int vrednost;
	@Column(unique = false, nullable = false)
	private int provera;
	
	public Ocena() {
		super();
	}

	public Ocena(Pacijent pacijent, int vrednost, int provera) {
		super();
		this.pacijent = pacijent;
		this.vrednost = vrednost;
		this.provera = provera;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public int getVrednost() {
		return vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}

	public int getProvera() {
		return provera;
	}

	public void setProvera(int provera) {
		this.provera = provera;
	}
	
}
