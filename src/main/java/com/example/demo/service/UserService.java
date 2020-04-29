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
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.unos.UserDTO;
import com.example.demo.model.korisnici.Korisnik;
import com.example.demo.repository.KorisnikRepository;

@Component
@Transactional(readOnly = true)
public class UserService {
		
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	
	@Transactional(readOnly = true)
	public Korisnik prijava(UserDTO user) {
		
		for (Korisnik k: this.korisnikRepository.findAll()) {
			if (k.getEmail().equals(user.getEmail()) && k.getLozinka().equals(user.getLozinka()) && k.isAktivan()) {
				
		        List<GrantedAuthority> lista = new ArrayList<>();
		        lista.add(new SimpleGrantedAuthority(k.isPromenjenaSifra() ? Hibernate.unproxy(k).getClass().getSimpleName() : "SIFRA"));
		        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(k.getId(), null, lista);
		        SecurityContextHolder.getContext().setAuthentication(token);
				return k;
				
			}
		}
		
		throw new RuntimeException();

	}
	
	@Transactional(readOnly = false)
	public void promenaSifre(Korisnik k, String sifra) {
		
		k.setLozinka(sifra);
		k.setPromenjenaSifra(true);
		this.korisnikRepository.save(k);
        List<GrantedAuthority> lista = new ArrayList<>();
        lista.add(new SimpleGrantedAuthority(Hibernate.unproxy(k).getClass().getSimpleName()));
        PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(k.getId(), null, lista);
        SecurityContextHolder.getContext().setAuthentication(token);
        
	}
	
	@Transactional(readOnly = true)
	public Korisnik getSignedKorisnik() {
		
		Authentication prava = SecurityContextHolder.getContext().getAuthentication();
        Integer id = Integer.parseInt(prava.getName());
        Korisnik k = this.korisnikRepository.getOne(id);	
        return (Korisnik) Hibernate.unproxy(k);
	
	}
	
}
