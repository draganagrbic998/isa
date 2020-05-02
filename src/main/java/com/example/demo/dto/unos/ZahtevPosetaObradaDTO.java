package com.example.demo.dto.unos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.demo.model.zahtevi.ZahtevPoseta;

public class ZahtevPosetaObradaDTO {
	
	private Integer id;
	private String lekar;
	private String pacijent;
	private boolean pregled;
	private String naziv;
	private String datum;
	private Integer idTipa;
	private Integer idSale;
	private Integer idLekar;
	private Integer idPacijent;
	private String kraj;
	private int sati;
	private int minuti;
	
	public ZahtevPosetaObradaDTO() {
		super();
	}
	
	public ZahtevPosetaObradaDTO(ZahtevPoseta zahtev) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		Calendar cal = Calendar.getInstance();
		this.id = zahtev.getId();
		this.datum = sdf.format(zahtev.getDatum());
		this.pacijent = zahtev.getKarton().getPacijent().getIme()+" "+zahtev.getKarton().getPacijent().getPrezime();
		this.lekar = zahtev.getLekar().getIme()+" "+zahtev.getLekar().getPrezime();
		this.naziv = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getNaziv() : null;
		this.idTipa = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getId() : null;
		this.pregled = zahtev.getTipPosete() != null ? zahtev.getTipPosete().getPregled() : null;
		this.idLekar = zahtev.getLekar().getId();
		this.idPacijent = zahtev.getKarton().getPacijent().getId();
		cal.setTime(zahtev.getDatum());
        cal.add(Calendar.HOUR, zahtev.getTipPosete().getSati());
        this.sati = zahtev.getTipPosete().getSati();
        cal.add(Calendar.MINUTE, zahtev.getTipPosete().getMinute());
        this.minuti = zahtev.getTipPosete().getMinute();
        
        this.kraj = sdf.format(cal.getTime());
	}

	public void osveziKraj() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(this.datum));
        cal.add(Calendar.HOUR, this.sati);
        cal.add(Calendar.MINUTE, this.minuti);
        this.kraj = sdf.format(cal.getTime());
	}
	
	
	
	public int getSati() {
		return sati;
	}

	public void setSati(int sati) {
		this.sati = sati;
	}

	public int getMinuti() {
		return minuti;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public Integer getIdPacijent() {
		return idPacijent;
	}

	public void setIdPacijent(Integer idPacijent) {
		this.idPacijent = idPacijent;
	}

	public Integer getIdLekar() {
		return idLekar;
	}

	public void setIdLekar(Integer idLekar) {
		this.idLekar = idLekar;
	}

	public Integer getIdTipa() {
		return idTipa;
	}

	public void setIdTipa(Integer idTipa) {
		this.idTipa = idTipa;
	}

	public Integer getIdSale() {
		return idSale;
	}

	public void setIdSale(Integer idSale) {
		this.idSale = idSale;
	}

	public String getLekar() {
		return lekar;
	}

	public void setLekar(String lekar) {
		this.lekar = lekar;
	}

	public String getPacijent() {
		return pacijent;
	}

	public void setPacijent(String pacijent) {
		this.pacijent = pacijent;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String tipPosete) {
		this.naziv = tipPosete;
	}
	
	public boolean isPregled() {
		return pregled;
	}

	public void setPregled(boolean pregled) {
		this.pregled = pregled;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
