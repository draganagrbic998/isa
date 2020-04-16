package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TipPoseteDTO;
import com.example.demo.dto.conversion.TipPoseteConversion;
import com.example.demo.model.Admin;
import com.example.demo.service.TipPoseteService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/tipPosete")
public class TipPoseteController {

	@Autowired
	private TipPoseteService tipPoseteService;
	
	@Autowired
	private TipPoseteConversion tipPoseteConversion;
	
	@Autowired
	private UserService userService;
		
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody TipPoseteDTO tipPoseteDTO) {
		if (this.tipPoseteService.isUnique(this.tipPoseteConversion.get(tipPoseteDTO))) {
			this.tipPoseteService.save(this.tipPoseteConversion.get(tipPoseteDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/vratiTipPosete", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipPoseteDTO> getType() {
		Admin admin = (Admin) this.userService.getSignedKorisnik();
		return this.tipPoseteConversion.get(this.tipPoseteService.findForAdmin(admin));
	}
	
	@PreAuthorize("hasAuthority('Pacijent')")
	@GetMapping(value="/svi/nazivi", produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<String> sviNazivi(){
		
		return this.tipPoseteService.sviTipovi();
		
	}
		
}
