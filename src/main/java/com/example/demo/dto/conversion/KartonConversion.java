package com.example.demo.dto.conversion;

import org.springframework.stereotype.Component;

import com.example.demo.dto.student1.KartonDTO;
import com.example.demo.model.Karton;

@Component
public class KartonConversion {
	
	//ovde napravite metodu koja ce od kartonDTO da napravi karton
	//to ce vam trebati kad budete menjali karton od strane lekara
	//konverzija liste kartona nam ne treba :D
	
	public KartonDTO get(Karton karton) {
		return new KartonDTO(karton);
	}

}