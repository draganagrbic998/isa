package com.example.demo.service;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.User;
import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Component
public class UserService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	public Korisnik prijava(@RequestBody User user) {
		
		for (Korisnik k: this.korisnikRepository.findAll()) {
			if (k.getEmail().equals(user.getEmail()) && k.getLozinka().equals(user.getLozinka())) {
				
				this.session.setAttribute("korisnik", k.getId());
				return k;
			}
		}
		
		return null;

	}
	
	
	public void odjava() {
		this.session.invalidate();
	}
	
	public boolean authorized(Class<?> klasa) {
		
		Integer id = (Integer) this.session.getAttribute("korisnik");
		if (id == null)
			return false;
		Korisnik k = this.korisnikRepository.getOne(id);
		if (!(klasa.isInstance(Hibernate.unproxy(k))))
			return false;
		return true;
		
	}
	
	public Korisnik getSignedKorisnik() {
		Integer id = (Integer) this.session.getAttribute("korisnik");
		if (id == null || !(this.korisnikRepository.existsById(id)))
			return null;
		return this.korisnikRepository.getOne(id);
	
	}
	
}
