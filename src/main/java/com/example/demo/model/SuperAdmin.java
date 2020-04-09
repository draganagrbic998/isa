package com.example.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("super")
public class SuperAdmin extends Korisnik{

	public SuperAdmin() {
		super();
	}

}
