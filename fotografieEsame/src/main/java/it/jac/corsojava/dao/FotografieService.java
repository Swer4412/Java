package it.jac.corsojava.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Fotografia;
import it.jac.corsojava.entity.Proprieta;
import it.jac.corsojava.exception.EntityNotFoundException;
import it.jac.corsojava.util.TransactionUtil;

//Questa classe serve per la gestione dell arraylist: riempimento, interrogazione

public class FotografieService {

	private static Logger log = LogManager.getLogger(FotografieService.class);
	
	public static final String FORMATO_JPG = "JPG";
	public static final String FORMATO_JPEG = "JPEG";
	public static final String FORMATO_PNG = "PNG";
	public static final String FORMATO_GIF = "GIF";
	public static final String FORMATO_WEBP = "WEBP";
	
	private FotografiaDao fotografiaDao = new FotografiaDao();
	private ProprietaDao proprietaDao = new ProprietaDao();
	
	public void create(Fotografia foto) {
		// Create non ritorna niente (void) ma se ci sono dei problemi, tira un errore creato da noi
		//Tutto questo per rimanere più autonomo dal main
		log.info("Creazione nuova Foto");
		
//		valorizzazione automatica delle informazioni di tracciamento
		//Questo posso farlo anche dento il dao ma fa lo stesso
		foto.setDataCreazione(LocalDateTime.now());
		foto.setUtenteCreazione("java");
		
		// Apro una nuova connessione e disabilito l'autocommit
		TransactionUtil.beginTransaction();
		try {
			
			this.fotografiaDao.save(foto);
			
			ArrayList<Proprieta> proprieta = foto.getProprieta();
			for(Proprieta prop : proprieta) {
				
				prop.setFotografia(foto);
				
				prop.setUtenteCreazione("java");
				prop.setDataCreazione(LocalDateTime.now());
	
				this.proprietaDao.save(prop);
			}
			
			//devo inviare il commit al database
			//TransactionUtil → currentConnection → connection; questo vuol dire che .commit() è la funzione di connection
			TransactionUtil.commit();
			
		} catch (Exception e) {
			//Se c'è qualunque problema, effettuo il rollback
			TransactionUtil.rollback();
			log.error("Errore salvataggio Fotografia", e);
		}
		
		log.info("Salvata Fotografia [id={}]", foto.getId());
	}

	
	public Fotografia findFotografiaById(int id) {
		return this.fotografiaDao.findById(id);
	}
	
	public ArrayList<Fotografia> findFotografieByNome(String desc) {
		return this.fotografiaDao.findByNome(desc);
		
	}
	
	public void delete(int id) throws EntityNotFoundException {
		
		log.info("Elimina Fotografia [id={}]", id);
		//Controllo che l'id corrisponda ad una fotografia esistente
		Fotografia persistedEntity = this.fotografiaDao.findById(id);
		if (persistedEntity == null) {
			log.warn("Fotografia [id={}] not found", id);
			throw new EntityNotFoundException("Fotografia non esiste");

		}
		
		//Devo iniziare la transazione per evitare di cancellare solo proprietà o solo la fotografia
		//Faccio quindi in modo che venga elminato o tutto o niente
		TransactionUtil.beginTransaction();
		try {
			List<Proprieta> proprieta = persistedEntity.getProprieta();
			for (Proprieta prop : proprieta) {
				this.proprietaDao.delete(prop.getId());
			}
			
			this.fotografiaDao.delete(id);
			
			TransactionUtil.commit();
			
		} catch (Exception e) {
			//Se c'è qualunque problema, effettuo il rollback
			TransactionUtil.rollback();
			log.error("Errore eliminazione Fotografia", e);
		}
		log.info("Fotografia eliminata");
	}
	
}
