package com.example.demo.dto.conversion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AdministratorKlinikeDTO;
import com.example.demo.model.Lokacija;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.model.Admin;
import com.example.demo.model.Klinika;

@Component
public class AdministratorKlinikeConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;

	public Admin get(AdministratorKlinikeDTO adminKlinikeDTO) {
		Admin admin = new Admin();
		admin.setId(adminKlinikeDTO.getId());
		admin.setEmail(adminKlinikeDTO.getEmailAdmin());
		admin.setLozinka(adminKlinikeDTO.getLozinkaAdmin());
		admin.setIme(adminKlinikeDTO.getImeAdmin());
		admin.setPrezime(adminKlinikeDTO.getPrezimeAdmin());
		admin.setTelefon(adminKlinikeDTO.getTelefonAdmin());
		admin.setLokacija(new Lokacija(adminKlinikeDTO.getNovaDrzava(), adminKlinikeDTO.getNoviGrad(), adminKlinikeDTO.getNovaAdresa()));
		//ovo samo za sada ovako da mozemo da testiramo
		if (adminKlinikeDTO.getNovaKlinika() != null) {
			Klinika klinika = this.klinikaRepository.getOne(adminKlinikeDTO.getNovaKlinika());
			admin.setKlinika(klinika);
		}
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
