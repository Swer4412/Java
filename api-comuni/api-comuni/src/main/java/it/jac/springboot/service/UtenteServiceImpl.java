package it.jac.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.jac.springboot.dao.UtenteRepository;
import it.jac.springboot.entity.Utente;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository dao;
	
	@Override
	public Utente findByUsername(String username) {
		
		return this.dao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Utente not found with username=" + username));
	}
	
}
