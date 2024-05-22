package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.SimulazioneEntity;
import com.example.demo.repository.SimulazioneRepository;
import com.example.demo.service.SimulazioneService;

//Il controller si occupa di gestire gli endpoint (definiti con @GetMapping), richiama 
//il service per fare le richieste al database e ritorna quello che il service ritorna

@RestController
@RequestMapping("/api/v1/auto")
public class SimulazioneController {
	SimulazioneService service;
	SimulazioneRepository repository;
	
	//Costruttore per prendere il service
	public SimulazioneController(SimulazioneService Service) {
		this.service = Service;
	}
	
	@GetMapping("/noleggi")
	public List<SimulazioneEntity> findAll(){
		
		return this.service.findAll();
	}

	
	@PostMapping("/noleggio")
	public String saveAllUsers(@RequestBody SimulazioneEntity entity){
		
		this.service.save(entity);
		
		return entity.getCodiceConferma();
	}
	
}
