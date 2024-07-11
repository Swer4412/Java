package com.example.demo.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Medico;
import com.example.demo.entity.Prenotazione;
import com.example.demo.entity.Utente;
import com.example.demo.repository.MedicoRepository;
import com.example.demo.repository.PrenotazioneRepository;
import com.example.demo.repository.UtenteRepository;

@Service
public class SimulazioneService {
	
	UtenteRepository utenteRepository;
	MedicoRepository medicoRepository;
	PrenotazioneRepository prenotazioneRepository;
	private static final String CHARACTERS = "01234CGMRJFFALDGGD56789";
    private static final SecureRandom random = new SecureRandom();
	public SimulazioneService(UtenteRepository utenteRepository, MedicoRepository medicoRepository, PrenotazioneRepository prenotazioneRepository) {
		this.utenteRepository = utenteRepository;
		this.medicoRepository = medicoRepository;
		this.prenotazioneRepository = prenotazioneRepository;
				
	}
	
	public List<Prenotazione> findAllPrenotazioni(){
		return this.prenotazioneRepository.findAll();
	}
	
	public List<Medico> findAllMedici(){
		return this.medicoRepository.findAll();
	}
	
	public String savePrenotazione(Prenotazione prenotazione) {
		prenotazione.setCodiceConferma(generateRandomString());
				
		this.prenotazioneRepository.save(prenotazione);
		
		return prenotazione.getCodiceConferma();
	}
	
	public Optional<Utente> findUserById(int id) {
		return this.utenteRepository.findById(id);
	}
	
	public ArrayList<Prenotazione> findPrenotazioniByIdUtente(int id) {
		return this.prenotazioneRepository.findAllById(id);
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
