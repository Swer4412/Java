package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Prenotazione;


@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
	
	//Dato che esiste un campo chiamato email, posso creare questa funzione chiamata FindByIdUtente perché
	//Jpa sa creare query dai nomi messi nelle funzioni:
	//Findby crea una query che fa una select, email serve per specificare su quale colonna filtrare con il 
	//parametro passato in input (email)
	List<Prenotazione> findByUtenteEmail(String email);
	
}
