package com.example.demo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.model.Poseta;

public class ObavezaDTO implements Comparable<ObavezaDTO>{

	private Integer id;
	private String datum;
	private String pocetak;
	private Integer trajanje;
	private String tip;
	private boolean pregled;
	private Date datumSortiranje;

	public ObavezaDTO() {
		super();
	}

	public ObavezaDTO(Poseta poseta) {
		SimpleDateFormat formatDatum = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatPocetak = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		this.id = poseta.getId();
		this.datum = formatDatum.format(poseta.getDatum());
		this.pocetak = formatPocetak.format(poseta.getDatum());
		this.trajanje = poseta.getTrajanje();
		this.tip = poseta.getTipPosete().getNaziv();
		this.pregled = poseta.getTipPosete().getPregled();
		this.datumSortiranje = poseta.getDatum();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}
	
	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
		this.pocetak = pocetak;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public Date getDatumSortiranje() {
		return datumSortiranje;
	}

	public void setDatumSortiranje(Date datumSortiranje) {
		this.datumSortiranje = datumSortiranje;
	}

	@Override
	public int compareTo(ObavezaDTO o) {
		return this.datumSortiranje.compareTo(o.datumSortiranje);
	}

}
