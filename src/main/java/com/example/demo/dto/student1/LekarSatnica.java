package com.example.demo.dto.student1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Lekar;

public class LekarSatnica extends LekarOcena {
	
	private List<Date> satnica;

	public LekarSatnica() {
		super();
	}

	public LekarSatnica(Lekar lekar) {
		super(lekar);
		this.satnica = new ArrayList<>();
	}

	public LekarSatnica(Lekar l, List<Date> satnica) {
		super(l);
		this.satnica = satnica;
	}

	public List<Date> getSatnica() {
		return satnica;
	}

	public void setSatnica(List<Date> satnica) {
		this.satnica = satnica;
	}

}
