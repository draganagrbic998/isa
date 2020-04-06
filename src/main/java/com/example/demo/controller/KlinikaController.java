package com.example.demo.controller;

import org.springframework.http.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.KlinikaDTO;
import com.example.demo.dto.conversion.KlinikaConversion;
import com.example.demo.service.KlinikaService;

@RestController
@RequestMapping(value = "/klinika")
public class KlinikaController {
	
	@Autowired
	private KlinikaService klinikaService;
	
	@Autowired
	private KlinikaConversion klinikaConversion;
	
	@GetMapping(value = "/pregled", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<KlinikaDTO> review(){
		return this.klinikaConversion.get(this.klinikaService.review());
	}

}
