package com.example.demo.model.ostalo;

import java.util.Date;

import com.example.demo.model.korisnici.Pacijent;

public interface Ocenjivanje {

	public Ocena refreshOcena(Pacijent pacijent, int ocena);

	Double prosecnaOcena();

	Double izracunajProfit(Date pocetak, Date kraj);
	
}
