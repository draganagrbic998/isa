package com.example.demo.dto;

import com.example.demo.model.Lekar;

public class LekarDTO extends ZaposleniDTO {

	private Integer specijalizacija;

	public LekarDTO() {
		super();
	}

	public LekarDTO(Lekar lekar) {
		super(lekar);
		this.specijalizacija = lekar.getSpecijalizacija().getId();
	}

	public Integer getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(Integer specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

}

