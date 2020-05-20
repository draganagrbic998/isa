package com.example.demo.simulacija;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.resursi.TipPosete;
import com.example.demo.service.TipPoseteService;


@RestController
public class IzmenaTipaPoseteSimulacija {

	//brisanje (izmena) tipa posete 1 
	
	@Autowired 
	private TipPoseteService tipPoseteService;
	
	@Scheduled(cron = "0 15 20 * * *")
	public void izmeniTipPoseteAdmin1() throws ParseException {
		
		System.out.println("ADMIN 1 KRENUO DA BRISE TIP POSETE");
		TipPosete tip = this.tipPoseteService.getOne(4);
		try {
			this.tipPoseteService.delete(tip.getId());
			System.out.println("ADMIN 1 IZBRISAO TIP POSETE 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 1 ZAKASNIO DA IZBRISE TIP POSETE 1!");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 1 ZAVRSIO SA RADOM");
	}
	
	@Scheduled(cron = "0 15 20 * * *")
	public void izmeniTipPoseteAdmin2() throws ParseException {
		
		System.out.println("ADMIN 2 KRENUO DA BRISE TIP POSETE");
		TipPosete tip = this.tipPoseteService.getOne(4);
		try {
			this.tipPoseteService.delete(tip.getId());
			System.out.println("ADMIN 2 IZBRISAO TIP POSETE 1");
		}
		catch(ObjectOptimisticLockingFailureException e) {
			System.out.println("ADMIN 2 ZAKASNIO DA IZBRISE TIP POSETE 1!");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("NEKA DRUGA GRESKA DESILA SE!");
		}
		System.out.println("ADMIN 2 ZAVRSIO SA RADOM");
	}
}