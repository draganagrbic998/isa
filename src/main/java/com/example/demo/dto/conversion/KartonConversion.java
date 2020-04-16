package com.example.demo.dto.conversion;

import org.springframework.stereotype.Component;

import com.example.demo.dto.student1.KartonDTO;
import com.example.demo.model.Karton;

@Component
public class KartonConversion {
		
	public KartonDTO get(Karton karton) {
		return new KartonDTO(karton);
	}

}