package com.example.demo.dto.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.pretraga.KalendarSalaDTO;
import com.example.demo.model.korisnici.Lekar;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Sala;



public class SalaDTO implements Comparable<SalaDTO>{

	private Integer id; 
	private String broj;
	private String naziv;
	private Integer klinika;
	private boolean aktivan;
	private Date prviSlobodan;
	private List<KalendarSalaDTO> kalendar;
	
	public SalaDTO() {
		super();
	}
	
	public SalaDTO(Sala sala) {
		Calendar cal = Calendar.getInstance();
		this.id = sala.getId();
		this.broj = sala.getBroj();
		this.naziv = sala.getNaziv();
		this.klinika = sala.getKlinika().getId();
		this.aktivan = sala.isAktivan();
		this.kalendar = new ArrayList<KalendarSalaDTO>();
		for (Poseta p : sala.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO)) {
				cal.setTime(p.getDatum());
		        cal.add(Calendar.HOUR, p.getTipPosete().getSati());
		        cal.add(Calendar.MINUTE, p.getTipPosete().getMinute());
		        Date kraj = cal.getTime();
		        this.kalendar.add(new KalendarSalaDTO(p.getDatum(), kraj));
			}
		}
	}
	public boolean proveriDatum(Date pocetak, Date kraj) {
		int brojac = 0;
		for (KalendarSalaDTO interval : this.getKalendar()) {
			if (pocetak.before(interval.getPocetak()) && (kraj.after(interval.getPocetak())) && (kraj.before(interval.getKraj()) || kraj.equals(interval.getKraj()))) {
				brojac++; }
			else if ((pocetak.after(interval.getPocetak()) || pocetak.equals(interval.getPocetak())) && (kraj.before(interval.getKraj()) || kraj.equals(interval.getKraj()))) { 
				brojac++; }
			else if ((pocetak.after(interval.getPocetak()) || pocetak.equals(interval.getPocetak())) && (kraj.after(interval.getKraj())) && pocetak.before(interval.getKraj()))  {
				brojac++; }
			else if(interval.getPocetak().after(pocetak) && interval.getKraj().before(kraj)){
				brojac++; }
			else {}
		}
		if (brojac != 0) { 
			return false;
		}
		return true;
	}
	public List<KalendarSalaDTO> getKalendar() {
		return kalendar;
	}

	public void setKalendar(List<KalendarSalaDTO> kalendar) {
		this.kalendar = kalendar;
	}

	public Date getPrviSlobodan() {
		return prviSlobodan;
	}

	public void setPrviSlobodan(Date prviSlobodan) {
		this.prviSlobodan = prviSlobodan;
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

	public Integer getKlinika() {
		return klinika;
	}

	public void setKlinika(Integer klinika) {
		this.klinika = klinika;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

	@Override
	public int compareTo(SalaDTO s) {
		return this.broj.compareTo(s.broj);
	}

	public void nadjiSlobodanTermin(String p, String k, Lekar l) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		Calendar pocetak = Calendar.getInstance();
		pocetak.setTime(f.parse(p));
		Calendar kraj = Calendar.getInstance();
		kraj.setTime(f.parse(k));
		while (!this.proveriDatum(pocetak.getTime(), kraj.getTime()) || !l.slobodan(pocetak.getTime(), kraj.getTime())) {
			pocetak.add(Calendar.HOUR, 1);
			kraj.add(Calendar.HOUR, 1);
		}
		this.prviSlobodan = pocetak.getTime();
		
	}
	
}
