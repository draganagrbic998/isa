package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AdminDTO;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.model.Admin;
import com.example.demo.model.Klinika;

@Component
public class AdministratorKlinikeConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;

	public Admin get(AdminDTO lekarDTO) {
		Admin admin = new Admin();
		admin.setId(lekarDTO.getId());
		admin.setEmail(lekarDTO.getEmail());
		admin.setLozinka(lekarDTO.getLozinka());
		admin.setIme(lekarDTO.getIme());
		admin.setPrezime(lekarDTO.getPrezime());
		admin.setTelefon(lekarDTO.getTelefon());
		admin.setDrzava(lekarDTO.getDrzava());
		admin.setAdresa(lekarDTO.getAdresa());
		admin.setGrad(lekarDTO.getGrad());
		Klinika klinika = this.klinikaRepository.getOne(lekarDTO.getKlinika());
		admin.setKlinika(klinika);
		return admin;
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
