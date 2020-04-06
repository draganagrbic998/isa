package com.example.demo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("lek")
public class Lek extends StavkaSifrarnika{
	
}
