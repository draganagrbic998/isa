package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Karton {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String brojOsiguranika;
	@Column
	private Double visina;
	@Column
	private Double tezina;
	@Column
	private Double levaDioptrija;
	@Column
	private Double desnaDioptrija;
	@Column
	private KrvnaGrupa krvnaGrupa;
	@OneToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;
	@OneToMany(mappedBy="karton", fetch = FetchType.EAGER)
	private Set<Poseta> posete;
	
	public Karton() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrojOsiguranika() {
		return brojOsiguranika;
	}

	public void setBrojOsiguranika(String brojOsiguranika) {
		this.brojOsiguranika = brojOsiguranika;
	}

	public Double getVisina() {
		return visina;
	}

	public void setVisina(Double visina) {
		this.visina = visina;
	}

	public Double getTezina() {
		return tezina;
	}

	public void setTezina(Double tezina) {
		this.tezina = tezina;
	}

	public Double getLevaDioptrija() {
		return levaDioptrija;
	}

	public void setLevaDioptrija(Double levaDioptrija) {
		this.levaDioptrija = levaDioptrija;
	}

	public Double getDesnaDioptrija() {
		return desnaDioptrija;
	}

	public void setDesnaDioptrija(Double desnaDioptrija) {
		this.desnaDioptrija = desnaDioptrija;
	}

	public KrvnaGrupa getKrvnaGrupa() {
		return krvnaGrupa;
	}

	public void setKrvnaGrupa(KrvnaGrupa krvnaGrupa) {
		this.krvnaGrupa = krvnaGrupa;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Set<Poseta> getPosete() {
		return posete;
	}

	public void setPosete(Set<Poseta> posete) {
		this.posete = posete;
	}
	
}
