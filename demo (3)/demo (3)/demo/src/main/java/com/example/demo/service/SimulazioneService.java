package com.example.demo.service;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SimulazioneEntity;
import com.example.demo.repository.SimulazioneRepository;

@Service
public class SimulazioneService {
	
	SimulazioneRepository repository;
	private static final String CHARACTERS = "01234CGMRJFFALDGGD56789";
    private static final SecureRandom random = new SecureRandom();
	public SimulazioneService(SimulazioneRepository Repository) {
		this.repository = Repository;
	}
	
	public List<SimulazioneEntity> findAll(){
		return this.repository.findAll();
	}
	
	public String save(SimulazioneEntity entity) {
		entity.setCodiceConferma(generateRandomString());
				
		this.repository.save(entity);
		
		return entity.getCodiceConferma();
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
