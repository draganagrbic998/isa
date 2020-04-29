package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Dijagnoza;
import com.example.demo.model.Izvestaj;
import com.example.demo.model.Lek;

public class IzvestajDTO {
	
	private Integer id;
	private String opis;
	private List<Integer> dijagnoze = new ArrayList<Integer>();
	private List<Integer> lekovi = new ArrayList<Integer>();
	private Integer poseta;
	private Integer terapija;
	private String posetaNaziv;
	
	public IzvestajDTO() {
		super();
	}

	public IzvestajDTO(Izvestaj izvestaj) {
		super();
		this.id = izvestaj.getId();
		this.opis = izvestaj.getOpis();
		this.poseta = izvestaj.getPoseta().getId();
		this.posetaNaziv = izvestaj.getPoseta().getTipPosete().getNaziv();
		this.terapija = izvestaj.getTerapija().getId();
		
		for (Dijagnoza d : izvestaj.getDijagnoze())
			this.dijagnoze.add(d.getId());
		
		for (Lek l : izvestaj.getTerapija().getLekovi())
			this.lekovi.add(l.getId());
	}
	
	public String getPosetaNaziv() {
		return posetaNaziv;
	}

	public void setPosetaNaziv(String posetaNaziv) {
		this.posetaNaziv = posetaNaziv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Integer getPoseta() {
		return poseta;
	}

	public void setPoseta(Integer poseta) {
		this.poseta = poseta;
	}

	public Integer getTerapija() {
		return terapija;
	}

	public void setTerapija(Integer terapija) {
		this.terapija = terapija;
	}
	
	public List<Integer> getDijagnoze() {
		return dijagnoze;
	}

	public void setDijagnoze(List<Integer> dijagnoze) {
		this.dijagnoze = dijagnoze;
	}

	public List<Integer> getLekovi() {
		return lekovi;
	}

	public void setLekovi(List<Integer> lekovi) {
		this.lekovi = lekovi;
	}
}
