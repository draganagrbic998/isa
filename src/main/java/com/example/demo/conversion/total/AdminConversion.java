package com.example.demo.conversion.total;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.dto.model.AdminDTO;
import com.example.demo.model.korisnici.Admin;

@Component
public class AdminConversion {
		
	@Autowired
	private KlinikaRepository klinikaRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	
		
	@Transactional(readOnly = true)
	public Admin get(AdminDTO adminDTO) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		long version;
		String lozinka;
		if (adminDTO.getId() != null) {
			version = this.adminRepository.getOne(adminDTO.getId()).getVersion();
			if (!adminDTO.getLozinka().equals(this.adminRepository.getOne(adminDTO.getId()).getLozinka()))
				lozinka = encoder.encode(adminDTO.getLozinka());
			else
				lozinka = adminDTO.getLozinka();
		}
		else {
			version = 0l;
			lozinka = encoder.encode(adminDTO.getLozinka());
		}
		
		
		
		return new Admin(adminDTO.getId(), adminDTO.getEmail(), lozinka, 
				adminDTO.getIme(), adminDTO.getPrezime(), adminDTO.getTelefon(), 
				adminDTO.getDrzava(), adminDTO.getGrad(), adminDTO.getAdresa(), 
				adminDTO.isAktivan(), adminDTO.isPromenjenaSifra(), 
				null, null, this.klinikaRepository.getOne(adminDTO.getKlinika()), version);
		
	}
	
	public AdminDTO get(Admin admin) {
		return new AdminDTO(admin);
	}
	
	public List<AdminDTO> get(List<Admin> admini){
		
		List<AdminDTO> adminiDTO = new ArrayList<>();
		for (Admin a: admini)
			adminiDTO.add(new AdminDTO(a));
		Collections.sort(adminiDTO);
		return adminiDTO;
		
	}
}
