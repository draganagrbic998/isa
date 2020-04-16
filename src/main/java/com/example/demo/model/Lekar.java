package com.example.demo.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("lekar")
public class Lekar extends Zaposleni implements Ocenjivanje{

	@ManyToOne
	@JoinColumn(name="specijalizacija")
	private TipPosete specijalizacija;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_ocena",
    joinColumns = @JoinColumn(name = "lekar"),
    inverseJoinColumns = @JoinColumn(name = "ocena"))
	private Set<Ocena> ocene = new HashSet<>();
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lekar_poseta",
    joinColumns = @JoinColumn(name = "lekar"),
    inverseJoinColumns = @JoinColumn(name = "poseta"))
	private Set<Poseta> posete = new HashSet<>();
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<ZahtevOdmor> odmorZahtevi = new HashSet<>();
	@OneToMany(mappedBy = "lekar", fetch = FetchType.EAGER)
	private Set<ZahtevPregled> pregledZahtevi = new HashSet<>();
	
	public Lekar() {
		super();
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

	public Set<ZahtevOdmor> getOdmorZahtevi() {
		return odmorZahtevi;
	}

	public void setOdmorZahtevi(Set<ZahtevOdmor> odmorZahtevi) {
		this.odmorZahtevi = odmorZahtevi;
	}

	public Set<ZahtevPregled> getPregledZahtevi() {
		return pregledZahtevi;
	}

	public void setPregledZahtevi(Set<ZahtevPregled> pregledZahtevi) {
		this.pregledZahtevi = pregledZahtevi;
	}

	@Override
	public Ocena refreshOcena(Pacijent pacijent, int ocena) {

		for (Ocena o: this.ocene) {
			if (o.getPacijent().getId().equals(pacijent.getId())) {
				o.setVrednost(ocena);
				return o;
			}
		}
		Ocena o = new Ocena(pacijent, ocena);
		this.ocene.add(o);
		return o;
		
	}
	
	public List<Date> getSatnica(Date datum) {
		// TODO Auto-generated method stub

		List<Date> satnica = new ArrayList<>();
		if (datum == null)
			return satnica;
		
		for  (ZahtevOdmor z: this.odmorZahtevi) {
			if (z.getOdobren() && (z.getPocetak().equals(datum) || z.getPocetak().before(datum)) && (z.getKraj().equals(datum) || z.getKraj().after(datum)))
				return satnica;
		}
		

		Calendar kalendar = Calendar.getInstance();
		kalendar.setTime(datum);
		kalendar.set(Calendar.HOUR_OF_DAY, 0);
		kalendar.set(Calendar.MINUTE, 0);
		Date compareDate = kalendar.getTime();
		
		List<Zauzetost> lista = new ArrayList<>();
		for (Poseta p: this.posete) {
			kalendar.setTime(p.datum());
			kalendar.set(Calendar.HOUR_OF_DAY, 0);
			kalendar.set(Calendar.MINUTE, 0);
			if (kalendar.getTime().equals(compareDate) && !p.getStanje().equals(StanjePosete.OBAVLJENO))
				lista.add(p);
		}
		for (ZahtevPregled zp: this.pregledZahtevi) {
			kalendar.setTime(zp.datum());
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
						kalendar.add(Calendar.MINUTE, 30);
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
			
			kalendar.setTime(p.datum());
			kalendar.add(Calendar.HOUR_OF_DAY, p.sati());
			kalendar.add(Calendar.MINUTE, p.minute());
			Date temp2 = kalendar.getTime();
			
			if ((start.after(p.datum()) || start.equals(p.datum())) && (start.before(temp2))) {
				kalendar.setTime(temp2);
				start = kalendar.getTime();
				sledeci = true;

			}
			
			else if ((temp1.after(p.datum())) && (temp1.before(temp2) || temp1.equals(temp2))) {
				kalendar.setTime(temp2);
				start = kalendar.getTime();
				sledeci = true;

			}
			
			else if (start.before(p.datum()) && temp1.after(temp2)) {
				kalendar.setTime(temp2);
				start = kalendar.getTime();
				sledeci = true;

			}
			


			
			else {
				satnica.add((Date) start.clone());
				kalendar.setTime(start);
				kalendar.add(Calendar.MINUTE, 30);
				start = kalendar.getTime();
				sledeci = false;
			}	
		}

		return satnica;
		
	}
	
}