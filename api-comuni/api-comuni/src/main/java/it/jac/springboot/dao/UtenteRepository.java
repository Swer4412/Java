package it.jac.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.jac.springboot.entity.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer>{

//	seguendo le linee guida posso definire dei metodi di ricerca senza implementazione 
//	https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#jpa.query-methods	
	Optional<Utente> findByUsername(String username);
}
