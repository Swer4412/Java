package it.jac.db.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.db.dao.DipendenteDao;
import it.jac.db.entity.Dipendente;
import it.jac.db.exception.EntityNotFoundException;

public class AuthService {

	private static Logger log = LogManager.getLogger(AuthService.class);
	
	private static AuthService instance = new AuthService();
	
	private DipendenteDao dipendenteDao = new DipendenteDao();

	private AuthService() {
		
	}
	
	public static AuthService getInstance() {
		return instance;
	}
	
	public Dipendente loginDipendente(String matricola) throws EntityNotFoundException {
		//Questa è una funzione ad alto livello per il login
		Dipendente result = null;
		
		//Cerco il dipendente in base alla matricola che mi ha passato
		result = this.dipendenteDao.findByMatricola(matricola);
		if (result == null) {
			log.warn("Matricola {} non registrata!", matricola);
			throw new EntityNotFoundException("Matricola non registrata a sistema");
		}
		
		return result;
	}
	
	public boolean loginAdmin(String password) {
		
		//Gestione semplificata: basta che la password sia admin123 per accedere come admin
		return "admin123".equals(password);
		
	}
}
