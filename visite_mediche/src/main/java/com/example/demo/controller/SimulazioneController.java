package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Medico;
import com.example.demo.entity.Prenotazione;
import com.example.demo.service.SimulazioneService;

import jakarta.servlet.http.HttpServletResponse;

//Il controller si occupa di gestire gli endpoint (definiti con @GetMapping), richiama 
//il service per fare le richieste al database e ritorna quello che il service ritorna

@RestController
@RequestMapping("/api/v1/visite")
public class SimulazioneController {
	SimulazioneService service;
	
	//Costruttore per prendere il service
	public SimulazioneController(SimulazioneService service) {
		this.service = service;
	}
	
	@PostMapping("/prenota")
	public String savePrenotazione(@RequestBody Prenotazione prenotazione, HttpServletResponse response){
		
		if (prenotazione.getOraVisita() > 18 || prenotazione.getOraVisita() < 8) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "";
		}
		
		this.service.savePrenotazione(prenotazione);
		
		return prenotazione.getCodiceConferma();
	}
	
	@GetMapping("/prenotazioni")
	public List<Prenotazione> findPrenotazioniByIdUtente(Long id){
		
		return this.service.findPrenotazioniByIdUtente(id);
	}
	
	@GetMapping("/medici")
	public List<Medico> findAllMedici(){
		
		return this.service.findAllMedici();
	}


	
	
}
