package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.ObradaZahtevRegistracijaDTO;
import com.example.demo.model.Karton;
import com.example.demo.model.Pacijent;
import com.example.demo.model.ZahtevRegistracija;
import com.example.demo.repository.KartonRepository;
import com.example.demo.repository.PacijentRepository;
import com.example.demo.repository.ZahtevRegistracijaRepository;
import com.example.demo.service.email.EmailService;
import com.example.demo.service.email.Message;

@Component
public class ZahtevRegistracijaService {

	private final String LINK_LOCAL = "http://localhost:8080/#/aktivirajNalog?id=";
	private final String LINK_HEROKU = "nasaaplikacija.herokuapp.com/#/aktivirajNalog?id=";

	@Autowired
	private ZahtevRegistracijaRepository zahtevRepository;

	@Autowired
	private PacijentRepository pacijentRepository;

	@Autowired
	private KartonRepository kartonRepository;

	@Autowired
	private EmailService emailService;

	public void save(ZahtevRegistracija zahtev) {
		this.zahtevRepository.save(zahtev);
	}

	public List<ZahtevRegistracija> findAll() {
		return this.zahtevRepository.findAll();
	}

	public ZahtevRegistracija nadji(ObradaZahtevRegistracijaDTO obradaZahtevDTO) {
		return zahtevRepository.getOne(obradaZahtevDTO.getId());
	}

	public void potvrdi(ZahtevRegistracija zahtev) {
		Pacijent pacijent = new Pacijent();
		pacijent.setEmail(zahtev.getEmail());
		pacijent.setLozinka(zahtev.getLozinka());
		pacijent.setIme(zahtev.getIme());
		pacijent.setPrezime(zahtev.getPrezime());
		pacijent.setTelefon(zahtev.getTelefon());
		pacijent.setDrzava(zahtev.getDrzava());
		pacijent.setGrad(zahtev.getGrad());
		pacijent.setAdresa(zahtev.getAdresa());
		pacijent.setPromenjenaSifra(true);
		pacijent.setAktivan(false);

		this.pacijentRepository.save(pacijent);

		for (Pacijent p : this.pacijentRepository.findAll()) {
			if (p.getEmail().equals(pacijent.getEmail())) {
				pacijent = p;
				break;
			}
		}

		Karton karton = new Karton();
		karton.setBrojOsiguranika(zahtev.getBrojOsiguranika());
		karton.setPacijent(pacijent);
		pacijent.setKarton(karton);

		this.kartonRepository.save(karton);
		this.pacijentRepository.save(pacijent);

		Message message = new Message(pacijent.getEmail(), "Registracija Uspesna!",
				"Molimo Vas da aktivirate svoj nalog klikom na link:\n" + LINK_HEROKU + pacijent.getId());

		emailService.sendMessage(message);
	}

	public void odbij(ZahtevRegistracija zahtev, String razlog) {
		Message message = new Message(zahtev.getEmail(), "Registracija Odbijena!",
				"Vasa registracija je odbijena iz sledecih razloga:\n\n" + razlog
						+ "\n\nMolimo Vas da pokusate ponovo.");

		emailService.sendMessage(message);
	}

	public void delete(ZahtevRegistracija zahtev) {
		this.zahtevRepository.delete(zahtev);
	}

}
