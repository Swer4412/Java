package it.jac.corsojava.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Entity;
import it.jac.corsojava.entity.NotaSpesa;
import it.jac.corsojava.entity.StatoSpesa;
import it.jac.corsojava.entity.VoceSpesa;

// rappresenta il gestore del nostro archivio
public class ServiceNoteSpesa {

	private static Logger log = LogManager.getLogger(ServiceNoteSpesa.class);

	private NoteSpesaDao dao = new NoteSpesaDao();

	public boolean registra(Entity entity) {
		
		//Impsto proprietà di nota_spesa
		NotaSpesa notaSpesa = entity.getNota_spesa();
		
		//Cerco l'id dalla matricola
		for (Entity ent : dao.read()) { //Per ogni entità trovata nel database
			if (ent.getDipendente().getMatricola() //Matricola presa dal database
				.equals(entity.getDipendente().getMatricola())) { //Matricola inserita dall' utente
				
				notaSpesa.setId_dipendente(ent.getDipendente().getId()); //Prendo l'id del dipendente di tale matricola
				break; //Una volta trovato, non serve più che si ripeta
				
			}
		}
		
		notaSpesa.setId_dipendente( //Imposto l'id del dipendente
				dao.read().get(dao.read().size()-1) //Trovo l'elemento dell'arraylist in ultima posizione
				.getDipendente().getId()); //Di questo elemento prendo dipendente e di dipendente prendo l'id
		
		notaSpesa.setStato(StatoSpesa.REGISTRATA);
		notaSpesa.setUtente_creazione("java");
		notaSpesa.setData_creazione(LocalDateTime.now());
		
		//Imposto proprietà per ogni istanza dentro la lista voci spesa
		double importo = 0;
		for (VoceSpesa voceSpesa : entity.getVoci_spesa()) {
			voceSpesa.setUtente_creazione("java");
			voceSpesa.setData_creazione(LocalDateTime.now());
			importo += voceSpesa.getImporto(); //Trovo l'importo totale
		}
		
		notaSpesa.setImporto_totale(importo);
		
		//Inserisco la entity completa nel database
		boolean success = dao.create(entity);

		return success;
	}
	
	public boolean elimina(int id) {
		
		for (Entity entity : dao.read()) {
			if (entity.getNota_spesa().getId() == id) { //Non riesco a capire equals e hashcode :(
				
				dao.delete(entity);
			
			}
		}
		
		return true;
	}
	
	public ArrayList<Entity> visualizza() {
		
		return dao.read();
	}
}
