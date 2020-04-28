package com.example.demo.dto;

public class PosetaDTOS2 {
	private Integer id;
	private Integer karton;
	private Integer tip;
	
	public PosetaDTOS2(Integer id, Integer karton, Integer tip) {
		super();
		this.id = id;
		this.karton = karton;
		this.tip = tip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKarton() {
		return karton;
	}

	public void setKarton(Integer karton) {
		this.karton = karton;
	}
	public Integer getTip() {
		return tip;
	}

	public void setTip(Integer tip) {
		this.tip = tip;
	}
	
	
	
}
