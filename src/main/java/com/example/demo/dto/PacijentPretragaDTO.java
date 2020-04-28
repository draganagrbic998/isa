package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Pacijent;

public class PacijentPretragaDTO extends PacijentDTO {
	
	Integer karton;
	KartonDTO kartonObj;
	private List<IzvestajDTO> stariIzvestaji;
	private Integer zakazanaPoseta;
	
	public PacijentPretragaDTO(Pacijent pacijent) {
		super(pacijent);
		this.kartonObj = new KartonDTO(pacijent.getKarton());
	}
	public Integer getKarton() {
		return karton;
	}

	public void setKarton(Integer karton) {
		this.karton = karton;
	}

	public KartonDTO getKartonObj() {
		return kartonObj;
	}

	public void setKartonObj(KartonDTO kartonObj) {
		this.kartonObj = kartonObj;
	}

	public List<IzvestajDTO> getStariIzvestaji() {
		return stariIzvestaji;
	}

	public void setStariIzvestaji(List<IzvestajDTO> stariIzvestaji) {
		this.stariIzvestaji = stariIzvestaji;
	}

	public Integer getZakazanaPoseta() {
		return zakazanaPoseta;
	}

	public void setZakazanaPoseta(Integer zakazanaPoseta) {
		this.zakazanaPoseta = zakazanaPoseta;
	}


}
