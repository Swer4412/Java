package com.example.demo.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Medico;
import com.example.demo.entity.Prenotazione;
import com.example.demo.entity.Utente;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PrenotazioneRepository;
import com.example.demo.repository.UtenteRepository;

@Service
public class AppService {
	
	UtenteRepository utenteRepository;
	MedicoRepository medicoRepository;
	PrenotazioneRepository prenotazioneRepository;
	private static final String CHARACTERS = "01234CGMRJFFALDGGD56789";
    private static final SecureRandom random = new SecureRandom();
    
    //Inizializzazione di tutte le repository 
	public AppService(UtenteRepository utenteRepository, MedicoRepository medicoRepository, PrenotazioneRepository prenotazioneRepository) {
		this.utenteRepository = utenteRepository;
		this.medicoRepository = medicoRepository;
		this.prenotazioneRepository = prenotazioneRepository;
	}
	
	//FUNZIONI GET
	public List<Prenotazione> findAllPrenotazioni(){
		return this.prenotazioneRepository.findAll();
	}
	
	public List<Medico> findAllMedici(){
		return this.medicoRepository.findAll();
	}
	
	public Utente findUserById(Long id) {
		return this.utenteRepository.findById(id).orElse(null);
	}
	
	public List<Prenotazione> findPrenotazioniByEmailUtente(String email) {
		return this.prenotazioneRepository.findByUtenteEmail(email);
	}

	public Utente findUtenteByEmail(String email) {
		return this.utenteRepository.findByEmail(email);
	}
	
	
	//FUNZIONI CREATE
	public String savePrenotazione(Prenotazione prenotazione) {
		prenotazione.setCodiceConferma(generateRandomString());

		this.prenotazioneRepository.save(prenotazione);
		
		return prenotazione.getCodiceConferma();
	}
	
	public void saveUtente(Utente utente) {	
		this.utenteRepository.save(utente);
	}
	

    public String generateRandomString() {
        int length = 6; // Lunghezza della stringa casuale
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }


	
	
	
	
	
}
