package com.example.demo.model.resursi;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.model.ostalo.Slobodnost;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;

@Entity
public class Sala implements Slobodnost{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String broj;
	@Column(unique = false, nullable = false)
	private String naziv;
	@ManyToOne
	@JoinColumn(name="klinika")
	private Klinika klinika;
	@Column(unique = false, nullable = false)
	private boolean aktivan;
	@OneToMany(mappedBy = "sala", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();

	public Sala() {
		super();
	}

	public Sala(Integer id, String broj, String naziv, Klinika klinika, boolean aktivan) {
		super();
		this.id = id;
		this.broj = broj;
		this.naziv = naziv;
		this.klinika = klinika;
		this.aktivan = aktivan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Klinika getKlinika() {
		return klinika;
	}

	public void setKlinika(Klinika klinika) {
		this.klinika = klinika;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Set<Poseta> getPosete() {
		return posete;
	}

	public void setPosete(Set<Poseta> posete) {
		this.posete = posete;
	}

	@Override
	public boolean slobodan(Date pocetak, Date kraj) {

		for (Poseta p: this.posete) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO)) {
				if ((pocetak.equals(p.pocetak()) || pocetak.after(p.pocetak()))
						&&  pocetak.before(p.kraj()))
					return false;
				if ((kraj.after(p.pocetak()))
						&& ( kraj.equals(p.kraj()) ||  kraj.before(p.kraj())))
					return false;
			}
		}
		return true;
	}
	
}
