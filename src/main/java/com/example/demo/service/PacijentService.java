package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Lekar;
import com.example.demo.model.Pacijent;
import com.example.demo.model.Poseta;
import com.example.demo.repository.PacijentRepository;

@Component
@Transactional(readOnly = true)
public class PacijentService {

	@Autowired
	private PacijentRepository pacijentRepository;
	
	@Transactional(readOnly = false)
	public void save(Pacijent pacijent) {
		this.pacijentRepository.save(pacijent);
	}

	@Transactional(readOnly = false)
	public boolean aktiviraj(Integer id) {
		
		Pacijent pacijent = this.pacijentRepository.getOne(id);
		
		if (pacijent.isAktivan())
			return false;
		
		pacijent.setAktivan(true);
		this.pacijentRepository.save(pacijent);
		
		return true;	
	}
	
	//pronalazim pacijente klinike
	//za sve lekare klinike, iz njihovih poseta uzimam karton i iz kartona uzimam pacijenta!
	/*@Transactional(readOnly = true)
	public List<Pacijent> findAll(List<Lekar> lekari) {
		List<Pacijent> pacijenti = new ArrayList<>();
		for (Lekar l : lekari) {
			if (l.getPosete() != null) {
				for (Poseta p : l.getPosete()) {
					if (p.getKarton()!=null && p.getKarton().getPacijent() != null) {
						if (!pacijenti.contains(p.getKarton().getPacijent())) {
							pacijenti.add(p.getKarton().getPacijent());
						}
					}
				}
			}
		}
		return pacijenti;
	}*/
	//lista pacijenata za lekara!
	@Transactional(readOnly = false)
	public List<Pacijent> nadjiPacijente(Lekar lekar) {
		List<Pacijent> pacijenti = new ArrayList<Pacijent>();
		for (Pacijent pacijent : this.pacijentRepository.findAll()) {
			for (Poseta poseta : pacijent.getKarton().getPosete()) {
				for (Lekar l : poseta.getLekari()) {
					if (l==lekar) {
						if (!pacijenti.contains(pacijent)) {
							pacijenti.add(pacijent);
						}
					}
				}
			}
		}
		return pacijenti;
	}
	
	
}
