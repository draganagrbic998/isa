package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AdministratorKlinikeDTO;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.model.Admin;
import com.example.demo.model.Klinika;

@Component
public class AdministratorKlinikeConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;

	public Admin get(AdministratorKlinikeDTO lekarDTO) {
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
	
	public AdministratorKlinikeDTO get(Admin admin) {
		return new AdministratorKlinikeDTO(admin);
	}
	
	public List<AdministratorKlinikeDTO> get(List<Admin> admini){
		
		List<AdministratorKlinikeDTO> adminKlinikeDTO = new ArrayList<AdministratorKlinikeDTO>();
		for (Admin a: admini)
			adminKlinikeDTO.add(new AdministratorKlinikeDTO(a));
		return adminKlinikeDTO;
		
	}
}
