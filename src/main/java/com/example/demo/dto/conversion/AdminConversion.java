package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AdminDTO;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.model.Admin;

@Component
public class AdminConversion {
		
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	public Admin get(AdminDTO adminDTO) {
		
		return new Admin(adminDTO.getId(), adminDTO.getEmail(), adminDTO.getLozinka(), 
				adminDTO.getIme(), adminDTO.getPrezime(), adminDTO.getTelefon(), 
				adminDTO.getDrzava(), adminDTO.getGrad(), adminDTO.getAdresa(), 
				adminDTO.isAktivan(), adminDTO.isPromenjenaSifra(), 
				null, null, this.klinikaRepository.getOne(adminDTO.getKlinika()));
		
	}
	
	public AdminDTO get(Admin admin) {
		return new AdminDTO(admin);
	}
	
	public List<AdminDTO> get(List<Admin> admini){
		
		List<AdminDTO> adminiDTO = new ArrayList<>();
		for (Admin a: admini)
			adminiDTO.add(new AdminDTO(a));
		return adminiDTO;
		
	}
}
