package it.jac.springboot.service;

import it.jac.springboot.entity.Utente;

public interface UtenteService {

	Utente findByUsername(String username);

}
