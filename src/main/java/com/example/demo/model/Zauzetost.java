package com.example.demo.model;

import java.util.Date;

public interface Zauzetost extends Comparable<Zauzetost>{
	
	public Date datum();
	public int sati();
	public int minute();

}
