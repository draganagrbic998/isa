package com.example.demo.dto.pretraga;

import java.util.List;

import com.example.demo.dto.unos.ZahtevPosetaObradaDTO;

public class GetPrviSlobodanDTO {

	private ZahtevPosetaObradaDTO zahtev;
	private Integer salaId;
	private List<Integer> lekari;

	public GetPrviSlobodanDTO() {
		super();
	}

	public ZahtevPosetaObradaDTO getZahtev() {
		return zahtev;
	}

	public void setZahtev(ZahtevPosetaObradaDTO zahtev) {
		this.zahtev = zahtev;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public List<Integer> getLekari() {
		return lekari;
	}

	public void setLekari(List<Integer> lekari) {
		this.lekari = lekari;
	}

}
