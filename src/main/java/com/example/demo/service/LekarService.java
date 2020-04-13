package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Admin;
import com.example.demo.model.Korisnik;
import com.example.demo.model.Lekar;
import com.example.demo.repository.KorisnikRepository;


@Component
public class LekarService {
	
	@Autowired 
	UserService userService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public void delete(Integer lekarId) {
		this.korisnikRepository.deleteById(lekarId);
	}
	public List<Lekar> findAll(){
		List<Lekar> doctors = new ArrayList<Lekar>();
		List<Korisnik> users = this.korisnikRepository.findAll(); 
		for (Korisnik k : users) {
			if (k.getClass().equals(Lekar.class)) {
				doctors.add((Lekar) k);
			}
		}
		return doctors;
	}
	//svi lekari iz klinike ulogovanog admina
	//otporno na null klinike kod lekara
	//podrazumevano da admin ima kliniku, tj da nije null!
	public List<Lekar> findAllOneClinic() {
		List<Lekar> doctors = new ArrayList<Lekar>();
		Admin loggedUser = (Admin) userService.getSignedKorisnik();
		List<Lekar> allDoctors = this.findAll();
		for (Lekar l : allDoctors) {
			if (l.getKlinika() != null) {
				if (l.getKlinika().equals(loggedUser.getKlinika())) {
					doctors.add(l);
			}}
		}
		return doctors;
	}
	
	public void save(Lekar lekar) {
		this.korisnikRepository.save(lekar);
	}
}
