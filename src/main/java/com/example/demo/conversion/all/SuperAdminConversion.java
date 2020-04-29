package com.example.demo.conversion.all;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.model.SuperAdminDTO;
import com.example.demo.model.korisnici.SuperAdmin;
import com.example.demo.repository.SuperAdminRepository;

@Component
public class SuperAdminConversion {
	
	@Autowired
	private SuperAdminRepository superAdminRepository;
	
	public SuperAdmin get(SuperAdminDTO superAdminDTO) {
		
		long version;
		if (superAdminDTO.getId() != null)
			version = this.superAdminRepository.getOne(superAdminDTO.getId()).getVersion();
		else
			version = 0l;
				
		return new SuperAdmin(superAdminDTO.getId(), superAdminDTO.getEmail(), superAdminDTO.getLozinka(), 
				superAdminDTO.getIme(), superAdminDTO.getPrezime(), superAdminDTO.getTelefon(), 
				superAdminDTO.getDrzava(), superAdminDTO.getGrad(), superAdminDTO.getAdresa(), 
				superAdminDTO.isAktivan(), superAdminDTO.isPromenjenaSifra(), version);
		
	}
	
	public SuperAdminDTO get(SuperAdmin superAdmin) {
		
		return new SuperAdminDTO(superAdmin);
		
	}
	
	public List<SuperAdminDTO> get(List<SuperAdmin> superAdmini){
		
		List<SuperAdminDTO> superAdminiDTO = new ArrayList<>();
		for (SuperAdmin sa: superAdmini)
			superAdminiDTO.add(new SuperAdminDTO(sa));
		Collections.sort(superAdminiDTO);
		return superAdminiDTO;
		
	}
	
}
