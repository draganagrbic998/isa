package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.SuperAdminDTO;
import com.example.demo.model.SuperAdmin;

@Component
public class SuperAdminConversion {

	public SuperAdmin get(SuperAdminDTO superAdminDTO) {
		
		return new SuperAdmin(superAdminDTO.getId(), superAdminDTO.getEmail(), superAdminDTO.getLozinka(), 
				superAdminDTO.getIme(), superAdminDTO.getPrezime(), superAdminDTO.getTelefon(), 
				superAdminDTO.getDrzava(), superAdminDTO.getGrad(), superAdminDTO.getAdresa(), 
				superAdminDTO.isAktivan(), superAdminDTO.isPromenjenaSifra());
		
	}
	
	public SuperAdminDTO get(SuperAdmin superAdmin) {
		
		return new SuperAdminDTO(superAdmin);
		
	}
	
	public List<SuperAdminDTO> get(List<SuperAdmin> superAdmini){
		
		List<SuperAdminDTO> superAdminiDTO = new ArrayList<>();
		for (SuperAdmin sa: superAdmini)
			superAdminiDTO.add(new SuperAdminDTO(sa));
		return superAdminiDTO;
		
	}
	
}
