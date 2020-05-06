package com.example.demo.dto.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.example.demo.dto.pretraga.PeriodDTO;
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
	private List<PeriodDTO> kalendar;
	
	public SalaDTO() {
		super();
	}
	
	public SalaDTO(Sala sala) {
		GregorianCalendar gc = new GregorianCalendar();
		this.id = sala.getId();
		this.broj = sala.getBroj();
		this.naziv = sala.getNaziv();
		this.klinika = sala.getKlinika().getId();
		this.aktivan = sala.isAktivan();
		this.kalendar = new ArrayList<>();
		for (Poseta p : sala.getPosete()) {
			if (!p.getStanje().equals(StanjePosete.OBAVLJENO)) {
				gc.setTime(p.getDatum());
		        gc.add(Calendar.HOUR, p.getTipPosete().getSati());
		        gc.add(Calendar.MINUTE, p.getTipPosete().getMinute());
		        Date kraj = gc.getTime();
		        this.kalendar.add(new PeriodDTO(p.getDatum(), kraj));
			}
		}
	}
	public boolean proveriDatum(Date pocetak, Date kraj) {
		for (PeriodDTO interval : this.getKalendar()) {
			if ((kraj.after(interval.getPocetak())) && (kraj.before(interval.getKraj()) || kraj.equals(interval.getKraj()))) {
				return false;
			}
			else if ((pocetak.after(interval.getPocetak()) || pocetak.equals(interval.getPocetak())) && (kraj.before(interval.getKraj()) || kraj.equals(interval.getKraj()))) { 
				return false;
			}
			else if ((pocetak.after(interval.getPocetak()) || pocetak.equals(interval.getPocetak())) && (kraj.after(interval.getKraj())) && pocetak.before(interval.getKraj()))  {
				return false;
			}
			else if(interval.getPocetak().after(pocetak) && interval.getKraj().before(kraj)){
				return false;
			}
		}
			
		return true;
	}
	public List<PeriodDTO> getKalendar() {
		return kalendar;
	}

	public void setKalendar(List<PeriodDTO> kalendar) {
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
		GregorianCalendar gc = new GregorianCalendar();
		GregorianCalendar gc1 = new GregorianCalendar();
		gc.setTime(f.parse(p));
		gc1.setTime(f.parse(k));
		while (!this.proveriDatum(gc.getTime(), gc1.getTime()) || !l.slobodan(gc.getTime(), gc1.getTime())) {
			gc.add(Calendar.HOUR_OF_DAY, 1);
			gc1.add(Calendar.HOUR_OF_DAY, 1);
		}
		this.prviSlobodan = gc.getTime();
		
	}

	public void nadjiSlobodanTermin(String p, String k, List<Lekar> lekari) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		GregorianCalendar pocetak = new GregorianCalendar();
		pocetak.setTime(f.parse(p));
		GregorianCalendar kraj = new GregorianCalendar();
		kraj.setTime(f.parse(k));
		
		for (Lekar l : lekari) {
			while (!this.proveriDatum(pocetak.getTime(), kraj.getTime()) || !l.slobodan(pocetak.getTime(), kraj.getTime())) {
				pocetak.add(Calendar.HOUR_OF_DAY, 1);
				kraj.add(Calendar.HOUR_OF_DAY, 1);
			}	
		}
		
		this.prviSlobodan = pocetak.getTime();		
	}
	
}
