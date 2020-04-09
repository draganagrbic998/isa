package com.example.demo.dto;

import com.example.demo.model.Lekar;

public class LekarDTO extends ZaposleniDTO{

	private String specijalizacija;

	public LekarDTO() {
		super();
	}

	public LekarDTO(Lekar lekar) {
		super(lekar);
		this.specijalizacija = lekar.getSpecijalizacija();
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

}

