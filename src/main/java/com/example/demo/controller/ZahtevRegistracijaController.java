package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ZahtevRegistracijaDTO;
import com.example.demo.dto.conversion.ZahtevRegistracijaConversion;
import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.service.ZahtevRegistracijaService;

@RestController
@RequestMapping(value = "/zahtevRegistracija")
public class ZahtevRegistracijaController {
	
	@Autowired
	private ZahtevRegistracijaService zahtevRegistracijaService;
	
	@Autowired
	private ZahtevRegistracijaConversion zahtevRegistracijaConversion;
	
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody ZahtevRegistracijaDTO zahtevDTO) {
		ZahtevRegistracija zahtev = zahtevRegistracijaConversion.get(zahtevDTO);
		this.zahtevRegistracijaService.create(zahtev);
	}

}
