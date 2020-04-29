package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.korisnici.Lekar;

public interface LekarRepository extends JpaRepository<Lekar, Integer>{

}
