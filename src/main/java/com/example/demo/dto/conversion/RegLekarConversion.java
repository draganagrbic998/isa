package com.example.demo.dto.conversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.dto.RegLekarDTO;
import com.example.demo.model.Lokacija;
import com.example.demo.repository.KlinikaRepository;
import com.example.demo.model.Lekar;

@Component
public class RegLekarConversion {

	@Autowired
	private KlinikaRepository klinikaRepository;

	public Lekar get(RegLekarDTO lekarDTO) {
		Lekar lekar = new Lekar();
		lekar.setEmail(lekarDTO.getEmailLekar());
		lekar.setLozinka(lekarDTO.getLozinkaLekar());
		lekar.setIme(lekarDTO.getImeLekar());
		lekar.setPrezime(lekarDTO.getPrezimeLekar());
		lekar.setTelefon(lekarDTO.getTelefonLekar());
		lekar.setSpecijalizacija(lekarDTO.getNovaSpecijalizacija());
		lekar.setLokacija(new Lokacija(lekarDTO.getNovaDrzava(), lekarDTO.getNoviGrad(), lekarDTO.getNovaAdresa()));
		//ovo je bilo neophodno ovako odraditi, jer ukoliko bih stavila lekara kao zaposlenog
		//dobijam gresku da treba onda Zaposlenog flush odraditi sto sada nije poenta
		this.klinikaRepository.findById(lekarDTO.getNovaKlinika()).get().setZaposleni(null);
		lekar.setKlinika(this.klinikaRepository.findById(lekarDTO.getNovaKlinika()).get());
		return lekar;
	}
	
	public RegLekarDTO get(Lekar lekar) {
		return new RegLekarDTO(lekar);
	}
}
