package com.example.demo.dto.student1;

import com.example.demo.model.Karton;
import com.example.demo.model.KrvnaGrupa;

public class KartonDTO {
	
	private Integer id;
	private String brojOsiguranika;
	private Double visina;
	private Double tezina;
	private Double levaDioptrija;
	private Double desnaDioptrija;
	private KrvnaGrupa krvnaGrupa;
	
	public KartonDTO() {
		super();
	}

	public KartonDTO(Karton karton) {
		super();
		this.id = karton.getId();
		this.brojOsiguranika = karton.getBrojOsiguranika();
		this.visina = karton.getVisina();
		this.tezina = karton.getTezina();
		this.levaDioptrija = karton.getLevaDioptrija();
		this.desnaDioptrija = karton.getDesnaDioptrija();
		this.krvnaGrupa = karton.getKrvnaGrupa();
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

}
