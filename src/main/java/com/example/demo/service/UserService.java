package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.example.demo.dto.User;
import com.example.demo.model.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Component
public class UserService {
		
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	public Korisnik prijava(User user) {
		
		for (Korisnik k: this.korisnikRepository.findAll()) {
			if (k.getEmail().equals(user.getEmail()) && k.getLozinka().equals(user.getLozinka()) && k.isAktivan()) {
				
		        List<GrantedAuthority> lista = new ArrayList<>();
		        lista.add(new SimpleGrantedAuthority(Hibernate.unproxy(k).getClass().getSimpleName()));
		        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(k.getId(), null, lista);
		        SecurityContextHolder.getContext().setAuthentication(token);

				return k;
			}
		}
		
		throw new RuntimeException();

	}
	

	
	public Korisnik getSignedKorisnik() {
		
		Authentication prava = SecurityContextHolder.getContext().getAuthentication();
        Integer id = Integer.parseInt(prava.getName());
        Korisnik k = this.korisnikRepository.getOne(id);	
        return (Korisnik) Hibernate.unproxy(k);
	
	}
	
}
