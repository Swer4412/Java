package it.jac.corsojava.dao;

import java.util.ArrayList; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Fotografia;

//Questa classe serve per la gestione dell arraylist: riempimento, interrogazione

public class ArchivioFotografie {

	private static Logger log = LogManager.getLogger(ArchivioFotografie.class);
	
	private static int SEQ = 0;
	
	private ArrayList<Fotografia> listFotografie = new ArrayList<>();
	
	public int nuovaFotografia(Fotografia foto) {
		
		//Id generato automaticamente
		foto.setId(SEQ++);
		
		//Aggiungo all arraylist l'istanza di foto ora completa
		this.listFotografie.add(foto);
		
		log.info("Nuova fotografia di id: {} ", foto.getId());
		
		return foto.getId();
		
	}
	
	public ArrayList<Fotografia> getListFotografie() {
		
		return this.listFotografie;
	}
	
	//Metodo comodo per tenere Main più pulito
	public Fotografia getFotografia(int id) {
		for (Fotografia f : listFotografie) {
			if (id == f.getId()) {
				log.info("Fotografia di id {} trovata ", id);
				return f;
			}
		}
		log.warn("Fotografia di id {} non trovata: ", id);
		return null;
	}
	
	public boolean cancellaFotografia(int id) {
		for (Fotografia f : this.listFotografie) {
			if (f.getId()==id) { //Guarda equals e hascode
				this.listFotografie.remove(f);
				log.info("Cancellata fotografia di id: {} ", id);
				return true;
			} 
		}
		//Nel caso non trova l'id
		log.warn("ID: {} non trovato", id);
		return false;
	}
	
	
}
