package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

import com.example.demo.dto.student1.BolestDTO;
import com.example.demo.dto.student1.Termin;

@Entity
public class Karton {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String brojOsiguranika;
	@Column(unique = false, nullable = true)
	private double visina;
	@Column(unique = false, nullable = true)
	private double tezina;
	@Column(unique = false, nullable = true)
	private double levaDioptrija;
	@Column(unique = false, nullable = true)
	private double desnaDioptrija;
	@Column(unique = false, nullable = true)
	private KrvnaGrupa krvnaGrupa;
	@OneToOne
	@JoinColumn(name="pacijent")
	private Pacijent pacijent;
	@OneToMany(mappedBy="karton", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();
	
	public Karton() {
		super();
	}
	
	public Karton(Integer id, String brojOsiguranika, double visina, double tezina, double levaDioptrija,
			double desnaDioptrija, KrvnaGrupa krvnaGrupa, Pacijent pacijent) {
		super();
		this.id = id;
		this.brojOsiguranika = brojOsiguranika;
		this.visina = visina;
		this.tezina = tezina;
		this.levaDioptrija = levaDioptrija;
		this.desnaDioptrija = desnaDioptrija;
		this.krvnaGrupa = krvnaGrupa;
		this.pacijent = pacijent;
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

	public double getVisina() {
		return visina;
	}

	public void setVisina(double visina) {
		this.visina = visina;
	}

	public double getTezina() {
		return tezina;
	}

	public void setTezina(double tezina) {
		this.tezina = tezina;
	}

	public double getLevaDioptrija() {
		return levaDioptrija;
	}

	public void setLevaDioptrija(double levaDioptrija) {
		this.levaDioptrija = levaDioptrija;
	}

	public double getDesnaDioptrija() {
		return desnaDioptrija;
	}

	public void setDesnaDioptrija(double desnaDioptrija) {
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

	public List<Termin> getTermini(){
		
		List<Termin> termini = new ArrayList<>();
		for (Poseta p: this.posete) {
			if (p.getStanje().equals(StanjePosete.ZAUZETO))
				termini.add(new Termin(p));
		}
		Collections.sort(termini);
		return termini;
		
	}
	
	public List<BolestDTO> getBolesti(){
		
		List<BolestDTO> bolesti = new ArrayList<>();
		for (Poseta p: this.posete) {
			if (p.getStanje().equals(StanjePosete.OBAVLJENO))
				bolesti.add(new BolestDTO(p));
		}
		Collections.sort(bolesti);
		return bolesti;
		
	}
	
}
