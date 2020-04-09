package com.example.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dijagnoza")
public class Dijagnoza extends StavkaSifrarnika{

	public Dijagnoza() {
		super();
	}
	
}
