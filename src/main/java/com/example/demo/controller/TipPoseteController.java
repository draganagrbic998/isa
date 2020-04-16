package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.service.TipPoseteService;

@RestController
@RequestMapping(value = "/tipPosete")
public class TipPoseteController {

	@Autowired
	private TipPoseteService tipPService;
	
	@Autowired
	private TipPoseteConversion tipPConversion;
		
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping(value = "/kreiranje", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> create(@RequestBody TipPoseteDTO tpDTO) {
		if (this.tipPService.isUnique(this.tipPConversion.get(tpDTO))) {
			this.tipPService.save(this.tipPConversion.get(tpDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/vratiTipPosete", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipPoseteDTO> getType() {
		return this.tipPConversion.get(this.tipPService.findForAdmin());
	}
		
}
