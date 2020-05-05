package com.example.demo.model.korisnici;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.dto.pretraga.ObavezaDTO;
import com.example.demo.model.ostalo.Ocena;
import com.example.demo.model.ostalo.Ocenjivanje;
import com.example.demo.model.ostalo.Slobodnost;
import com.example.demo.model.ostalo.Zauzetost;
import com.example.demo.model.posete.Poseta;
import com.example.demo.model.posete.StanjePosete;
import com.example.demo.model.resursi.Klinika;
import com.example.demo.model.resursi.TipPosete;
import com.example.demo.model.zahtevi.ZahtevOdmor;
import com.example.demo.model.zahtevi.ZahtevPoseta;

@Entity
@DiscriminatorValue("lekar")
public class Lekar extends Zaposleni implements Ocenjivanje, Slobodnost{

	@ManyToOne
	@JoinColumn(name="specijalizacija")
	private TipPosete specijalizacija;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_ocena",
    joinColumns = @JoinColumn(name = "lekar"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene = new HashSet<>();
	@ManyToMany(mappedBy = "lekari", fetch = FetchType.EAGER)
	private Set<Poseta> posete = new HashSet<>();
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<ZahtevPoseta> posetaZahtevi = new HashSet<>();
	@Column
	private Date poslednjaIzmena;
	@OneToOne
	@JoinColumn(name="zapocetaPoseta")
	private Poseta zapocetaPoseta;
	
	public Lekar() {
		super();
	}

	public Lekar(Integer id, String email, String lozinka, String ime, String prezime, String telefon, String drzava,
			String grad, String adresa, boolean aktivan, boolean promenjenaSifra, Date pocetnoVreme, Date krajnjeVreme,
			Klinika klinika, TipPosete specijalizacija, long version) {
		super(id, email, lozinka, ime, prezime, telefon, drzava, grad, adresa, aktivan, promenjenaSifra, pocetnoVreme,
				krajnjeVreme, klinika, version);
		this.specijalizacija = specijalizacija;
	}

	public TipPosete getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(TipPosete specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public Set<Ocena> getOcene() {
		return ocene;
	}

	public void setOcene(Set<Ocena> ocene) {
		this.ocene = ocene;
	}

	public Set<Poseta> getPosete() {
		return posete;
	}

	public void setPosete(Set<Poseta> posete) {
		this.posete = posete;
	}

	public Set<ZahtevPoseta> getPosetaZahtevi() {
		return posetaZahtevi;
	}

	public void setPosetaZahtevi(Set<ZahtevPoseta> posetaZahtevi) {
		this.posetaZahtevi = posetaZahtevi;
	}

	public Date getPoslednjaIzmena() {
		return poslednjaIzmena;
	}

	public void setPoslednjaIzmena(Date poslednjaIzmena) {
		this.poslednjaIzmena = poslednjaIzmena;
	}

	public Poseta getZapocetaPoseta() {
		return zapocetaPoseta;
	}

	public void setZapocetaPoseta(Poseta zapocetaPoseta) {
		this.zapocetaPoseta = zapocetaPoseta;
	}

	@Override
	public Ocena refreshOcena(Pacijent pacijent, int ocena) {

		for (Ocena o: this.ocene) {
			if (o.getPacijent().getId().equals(pacijent.getId())) {
				o.setVrednost(ocena);
				return o;
			}
		}
		Ocena o = new Ocena(pacijent, ocena, this.getId() + "L");
		this.ocene.add(o);
		return o;
		
	}
	
	public List<Date> getSatnica(Date datum) {

		List<Date> satnica = new ArrayList<>();
		if (datum == null)
			return satnica;
		
		for  (ZahtevOdmor z: this.getOdmorZahtevi()) {
			if ((z.getPocetak().equals(datum) || z.getPocetak().before(datum)) && (z.getKraj().equals(datum) || z.getKraj().after(datum)))
				return satnica;
		}
		

		GregorianCalendar kalendar = new GregorianCalendar();
		kalendar.setTime(datum);
		kalendar.set(Calendar.HOUR_OF_DAY, 0);
		kalendar.set(Calendar.MINUTE, 0);
		Date compareDate = kalendar.getTime();
		
		List<Zauzetost> lista = new ArrayList<>();
		for (Poseta p: this.posete) {
			kalendar.setTime(p.pocetak());
			kalendar.set(Calendar.HOUR_OF_DAY, 0);
			kalendar.set(Calendar.MINUTE, 0);
			if (kalendar.getTime().equals(compareDate) && !p.getStanje().equals(StanjePosete.OBAVLJENO))
				lista.add(p);
		}
		for (ZahtevPoseta zp: this.posetaZahtevi) {
			kalendar.setTime(zp.pocetak());
			kalendar.set(Calendar.HOUR_OF_DAY, 0);
			kalendar.set(Calendar.MINUTE, 0);
			if (kalendar.getTime().equals(compareDate))
				lista.add(zp);


		}
		
		
		Collections.sort(lista);

		
		kalendar.setTime(this.getPocetnoVreme());
		int sati = kalendar.get(Calendar.HOUR_OF_DAY);
		int minute = kalendar.get(Calendar.MINUTE);
		kalendar.setTime(datum);
		kalendar.set(Calendar.HOUR_OF_DAY, sati);
		kalendar.set(Calendar.MINUTE, minute);
		Date start = kalendar.getTime();

		kalendar.setTime(this.getKrajnjeVreme());
		sati = kalendar.get(Calendar.HOUR_OF_DAY);
		minute = kalendar.get(Calendar.MINUTE);
		kalendar.setTime(datum);
		kalendar.set(Calendar.HOUR_OF_DAY, sati);
		kalendar.set(Calendar.MINUTE, minute);
		Date end = kalendar.getTime();

		Iterator<Zauzetost> iterator = lista.iterator();
		Zauzetost p = null;
		boolean sledeci = true;

		while (true) {
			
			kalendar.setTime(start);
			kalendar.add(Calendar.HOUR_OF_DAY, this.specijalizacija.getSati());
			kalendar.add(Calendar.MINUTE, this.specijalizacija.getMinute());
			Date temp1 = kalendar.getTime();

			if (temp1.after(end))
				break;

			
			if (sledeci) {
				
				if (!iterator.hasNext()) {
					
					while (temp1.before(end) || temp1.equals(end)) {
						
						satnica.add((Date) start.clone());
						kalendar.setTime(start);
						kalendar.add(Calendar.HOUR, this.specijalizacija.getSati());
						kalendar.add(Calendar.MINUTE, this.specijalizacija.getMinute());
						start = kalendar.getTime();
						kalendar.setTime(start);
						kalendar.add(Calendar.HOUR_OF_DAY, this.specijalizacija.getSati());
						kalendar.add(Calendar.MINUTE, this.specijalizacija.getMinute());
						temp1 = kalendar.getTime();

					}
					
					break;
				}
				
				p = iterator.next();
				
			}
			
			kalendar.setTime(p.pocetak());
			kalendar.add(Calendar.HOUR_OF_DAY, p.sati());
			kalendar.add(Calendar.MINUTE, p.minute());
			Date temp2 = kalendar.getTime();
			
			if ((start.after(p.pocetak()) || start.equals(p.pocetak())) && (start.before(temp2))) {
				kalendar.setTime(temp2);
				start = kalendar.getTime();
				sledeci = true;

			}
			
			else if ((temp1.after(p.pocetak())) && (temp1.before(temp2) || temp1.equals(temp2))) {
				kalendar.setTime(temp2);
				start = kalendar.getTime();
				sledeci = true;

			}
			
			else if (start.before(p.pocetak()) && temp1.after(temp2)) {
				kalendar.setTime(temp2);
				start = kalendar.getTime();
				sledeci = true;

			}
			


			
			else {
				satnica.add((Date) start.clone());
				kalendar.setTime(start);
				kalendar.add(Calendar.HOUR, this.specijalizacija.getSati());
				kalendar.add(Calendar.MINUTE, this.specijalizacija.getMinute());
				start = kalendar.getTime();
				sledeci = false;
			}	
		}

		return satnica;
		
	}
	
	

	public List<ObavezaDTO> getObaveze() {
		List<ObavezaDTO> obaveze = new ArrayList<>();

		for (Poseta p : this.posete) {
			if (p.getStanje().equals(StanjePosete.ZAUZETO))
				obaveze.add(new ObavezaDTO(p));
		}

		return obaveze;
	}
	
	
	public boolean proveriZahteve(Date pocetak, Date kraj, Integer id) {
		for (ZahtevPoseta p: this.posetaZahtevi) {
			if (!p.getId().equals(id)) {
				if ((pocetak.equals(p.pocetak()) || pocetak.after(p.pocetak()))
					&&  pocetak.before(p.kraj())) {
					System.out.println("pao sam na proveri zahteve 1");
					return false; }
				if ((kraj.after(p.pocetak())) 
					&& ( kraj.equals(p.kraj()) ||  kraj.before(p.kraj()))) {
					System.out.println("pao sam na proveri zahteve 2");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean slobodan(Date pocetak, Date kraj) {
		
		if (!this.isAktivan())
			return false;
		GregorianCalendar gc = new GregorianCalendar();	
		
		gc.setTime(pocetak);
		Double vremePocetak = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;
		gc.setTime(kraj);
		Double vremeKraj = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;

		gc.setTime(this.getPocetnoVreme());
		Double smenaPocetak = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;
		
		gc.setTime(this.getKrajnjeVreme());
		Double smenaKraj = gc.get(Calendar.HOUR_OF_DAY)+gc.get(Calendar.MINUTE)/60.0;
		
		
		gc.setTime(pocetak);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		Date pocetakDatum = gc.getTime();
		gc.setTime(kraj);
		gc.set(Calendar.HOUR, 0);
		gc.set(Calendar.MINUTE, 0);
		Date krajDatum = gc.getTime();
		

		if (vremePocetak < smenaPocetak) {
			return false;
		}
		
		if (vremeKraj > smenaKraj) {
			return false;
		}
		
		if (vremeKraj < smenaPocetak) {
			return false;
		}
				
		for (ZahtevOdmor z: this.getOdmorZahtevi()) {
			if (z.getPocetak().equals(pocetakDatum) || z.getKraj().equals(krajDatum))
				return false;
		}
		
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

	@Override
	public double prosecnaOcena() {
		if (this.ocene.isEmpty())
			return 0.0;
		double suma = 0.0;
		for (Ocena o : this.ocene)
			suma += o.getVrednost();
		return suma/this.ocene.size();
	}

	@Override
	public double izracunajProfit(Date pocetak, Date kraj) {
		return 0.0;
	}
	
	
}
