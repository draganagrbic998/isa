package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Lekar;

public class LekarSatnice extends LekarOcena{
	
	private List<Date> satnica;

	public LekarSatnice() {
		super();
	}

	public LekarSatnice(Lekar lekar) {
		super(lekar);
		this.satnica = new ArrayList<>();
	}

	public LekarSatnice(Lekar l, List<Date> satnica2) {
		super(l);
		this.satnica = satnica2;
	}

	public List<Date> getSatnica() {
		return satnica;
	}

	public void setSatnica(List<Date> satnica) {
		this.satnica = satnica;
	}

}
