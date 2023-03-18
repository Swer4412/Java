package it.jac.db.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.db.dao.CategoriaDao;
import it.jac.db.dao.DipendenteDao;
import it.jac.db.dao.NotaSpesaDao;
import it.jac.db.dao.VoceSpesaDao;
import it.jac.db.entity.Categoria;
import it.jac.db.entity.Dipendente;
import it.jac.db.entity.NotaSpesa;
import it.jac.db.entity.VoceSpesa;
import it.jac.db.exception.EntityNotFoundException;
import it.jac.db.exception.ValidateNotaSpesaException;
import it.jac.db.util.TransactionUtil;

public class NotaSpesaService {

	private static Logger log = LogManager.getLogger(NotaSpesaService.class);
	
	private static NotaSpesaService instance = new NotaSpesaService();
	
	public static final String STATO_REGISTRATA = "REGISTRATA";
	public static final String STATO_IN_VALIDAZIONE = "IN_VALIDAZIONE";
	public static final String STATO_VALIDA = "VALIDA";
	public static final String STATO_RIFIUTATA = "RIFIUTATA";
	
	//Il service collabora con i dao
	private NotaSpesaDao notaSpesaDao = new NotaSpesaDao();
	private DipendenteDao dipendenteDao = new DipendenteDao();
	private VoceSpesaDao voceSpesaDao = new VoceSpesaDao();
	private CategoriaDao categoriaDao = new CategoriaDao();
	
	private NotaSpesaService() {
		//Dichiariamo privata la classe
	}
	
	public static NotaSpesaService getInstance() {
		//L'Unico modo per prendere l'istanza statica (solo una e creata appena parte il programma)
		return instance;
	}
	
	public void create(NotaSpesa notaSpesa) throws EntityNotFoundException {
		// Create non ritorna niente (void) ma se ci sono dei problemi, tira un errore creato da noi
		//Tutto questo per rimanere più autonomo dal main
		log.info("Creazione nuova NotaSpesa");

//		controllo se il dipendente relativo alla nota spesa esiste
		Dipendente dipendente = notaSpesa.getDipendente(); //notaSpesa ha un campo di tipo Dipendente
		Dipendente dipPersisted = this.dipendenteDao.findById(dipendente.getId());
		//Un oggetto è persistente se esite il record
		if (dipPersisted == null) {
			log.warn("Dipendente [id={}] not found", dipendente.getId());
			throw new EntityNotFoundException("Dipendente non esiste");
			// EntityNOtFOundException è una istanza che abbiamo creato noi
		}
		
		notaSpesa.setDipendente(dipPersisted);

//		valorizzazione automatica del codice
		//L'utente farebbe fatica a registrare un codice come 2023_MI_0000071_0010
		notaSpesa.setCod(dipPersisted.getMatricola() + "_" + notaSpesa.getMeseRif());
		
//		valorizzazione automatica dello stato iniziale
		notaSpesa.setStato(STATO_REGISTRATA);
		
//		valorizzazione automatica delle informazioni di tracciamento
		//Questo posso farlo anche dento il dao ma fa lo stesso
		notaSpesa.setDataCreazione(LocalDateTime.now());
		notaSpesa.setUtenteCreazione("java");

		notaSpesa.setImportoTotale(
				notaSpesa.getVociSpesa()
				.stream() // stream = Strutture dati che rappresentano un insieme di elementi analizzabili
				.mapToDouble(f -> f.getImporto()).sum());
		
		
		// Apro una nuova connessione e disabilito l'autocommit
		TransactionUtil.beginTransaction();
		try {
		
	//		Inserisco la nota spesa
			this.notaSpesaDao.save(notaSpesa);
			
	//		salvo le voci spesa
			//notaspesa contine una proprietà list<VoceSpesa>
			List<VoceSpesa> dettaglioList = notaSpesa.getVociSpesa();
			for(VoceSpesa v : dettaglioList) {
				
	//			controllo che la categoria della voce esista
				Categoria catPersisted = this.categoriaDao.findById(v.getCategoria().getId());
				if (catPersisted == null) {
					log.warn("Categoria [id={}] not found", v.getCategoria().getId());
					throw new EntityNotFoundException("Categoria non esiste");				
				}
	//			assegno alla voce spesa la categoria relativa
				v.setNotaSpesa(notaSpesa);
				
				v.setUtenteCreazione("java");
				v.setDataCreazione(LocalDateTime.now());
	
	//			salvo la voce spesa...
	//			ATTENZIONE! cosa succede una di queste operazioni non va a buon fine?
	//			ormai la testata della nota spesa è stata inserita!
	//			abbiamo bisogno di "eseguire" queste operazioni come se fossero 1 sola
	//			TODO gestire la TRANSAZIONE
				this.voceSpesaDao.save(v); //Se non va una nota spesa, ci troviamo una nota spesa
			}
			
			//devo inviare il commit al database
			TransactionUtil.commit();
			
		} catch (Exception e) {
			//Se c'è qualunque problema, effettuo il rollback
			TransactionUtil.rollback();
			log.error("Errore salvataggio Nota Spesa", e);
		}
		
		log.info("Salvata NotaSpesa [id={}]", notaSpesa.getId());
	}

	private void update(NotaSpesa persistedEntity, NotaSpesa notaSpesa) throws EntityNotFoundException {
		
		log.info("Modifica NotaSpesa [id={}]", persistedEntity.getId());
		
//		controllo se il dipendente relativo alla nota spesa esiste		
		Dipendente dipendente = notaSpesa.getDipendente();
		Dipendente dipPersisted = this.dipendenteDao.findById(dipendente.getId());
		if (dipPersisted == null) {
			log.warn("Dipendente [id={}] not found", dipendente.getId());
			throw new EntityNotFoundException("Dipendente non esiste");
		}
		
//		copio tutte le properties sull'entità appena caricata
		persistedEntity.setCod(notaSpesa.getCod());
		persistedEntity.setMeseRif(notaSpesa.getMeseRif());
		persistedEntity.setImportoTotale(notaSpesa.getImportoTotale());
		persistedEntity.setStato(notaSpesa.getStato());
		persistedEntity.setDipendente(notaSpesa.getDipendente());
		persistedEntity.setDataModifica(LocalDateTime.now());
		persistedEntity.setUtenteModifica("java");
		
		this.notaSpesaDao.update(persistedEntity);
		
//		rimuovo tutte le voci spesa esistenti
		List<VoceSpesa> vociPersistedList = persistedEntity.getVociSpesa();
		for(VoceSpesa v : vociPersistedList) {
			
			this.voceSpesaDao.delete(v.getId());
		}
		
//		salvo le voci spesa (dopo averle rimosse poco prima equivale all'aggiornamento)
		List<VoceSpesa> dettaglioList = notaSpesa.getVociSpesa();
		for(VoceSpesa v : dettaglioList) {
			
//			controllo che la categoria della voce esista
			Categoria catPersisted = this.categoriaDao.findById(v.getCategoria().getId());
			if (catPersisted == null) {
				log.warn("Categoria [id={}] not found", v.getCategoria().getId());
				throw new EntityNotFoundException("Categoria non esiste");				
			}
			v.setNotaSpesa(persistedEntity);
			
			v.setUtenteCreazione("java");
			v.setDataCreazione(LocalDateTime.now());

			this.voceSpesaDao.save(v);
		}

		log.info("Modificata NotaSpesa [id={}]", persistedEntity.getId());
	}

	public void delete(int id, String matricola) throws EntityNotFoundException, ValidateNotaSpesaException {

		log.info("Elimina NotaSpesa [id={}]");
		
		NotaSpesa persistedEntity = this.notaSpesaDao.findById(id);
		if (persistedEntity == null) {
			log.warn("NotaSpesa [id={}] not found", id);
			throw new EntityNotFoundException("NotaSpesa non esiste");

		}
//		controllo che la nota spesa sia della matricola che lo ha richiesto
		if (!persistedEntity.getDipendente().getMatricola().equalsIgnoreCase(matricola)) {
			
			log.warn("Matricola [{}] non coerente con la nota spesa [id={}]", matricola, id);
			throw new ValidateNotaSpesaException("Matricola non coerente con la nota spesa");
		}
		
//		TODO anche qui bisogna gestire una transazione
//		ci sono diverse operazioni che vengono svolte ma dovrebbero
//		essere interpretate come 1 sola
		List<VoceSpesa> vociSpesa = persistedEntity.getVociSpesa();
		for (VoceSpesa voceSpesa : vociSpesa) {
			this.voceSpesaDao.delete(voceSpesa.getId());
		}
		
		this.notaSpesaDao.delete(id);
		
		log.info("NotaSpesa eliminata");
	}
	
	public NotaSpesa findNotaSpesaById(int id) {
		
		return this.notaSpesaDao.findById(id);
	}
	
	public List<NotaSpesa> findNoteSpesaByMatricola(String matricola) {
		
		return this.notaSpesaDao.findByMatricola(matricola);
	}

	public List<NotaSpesa> findNoteSpesaByStato(String stato) {
		
		return this.notaSpesaDao.findByStato(stato);
	}
	
	public Dipendente findDipendenteByMatricola(String matricola) throws EntityNotFoundException {
		
		Dipendente entity = this.dipendenteDao.findByMatricola(matricola);
		if (entity == null) {
			log.warn("Dipendente [matricola={}] non trovata", matricola);
			throw new EntityNotFoundException("Dipendente non trovato");
		}
		
		return entity;
	}

	public Categoria findCategoriaByCod(String cod) throws EntityNotFoundException {
		
		Categoria entity = this.categoriaDao.findByCod(cod);
		if (entity == null) {
			log.warn("Categoria [cod={}] non trovata", cod);
			throw new EntityNotFoundException("Categoria non trovata");
		}
		
		return entity;
	}
	
	public void prendiInCaricoNotaSpesa(int id) throws EntityNotFoundException, ValidateNotaSpesaException {
		
		log.info("Prendo in carico NotaSpesa [id={}]", id);
		
//		controllo se la nota spesa esiste
		NotaSpesa persistedEntity = this.notaSpesaDao.findById(id);
		if (persistedEntity == null) {
			log.warn("NotaSpesa [id={}] not found", id);
			throw new EntityNotFoundException("NotaSpesa non esiste");
		}
		
//		controllo che la nota spesa sia nello stato corretto
		if (!STATO_REGISTRATA.equals(persistedEntity.getStato())) {
			log.warn("NotaSpesa [id={}] non in stato REGISTRATA", id);
			throw new ValidateNotaSpesaException("NotaSpesa non in stato REGISTRATA");
		}
		
		persistedEntity.setStato(STATO_IN_VALIDAZIONE);
		update(persistedEntity, persistedEntity);
		
	}

	public void approvaNotaSpesa(int id, NotaSpesa notaSpesa) throws EntityNotFoundException, ValidateNotaSpesaException {
		
		log.info("Approvazione NotaSpesa [id={}]", id);

//		controllo se la nota spesa esiste
		NotaSpesa persistedEntity = this.notaSpesaDao.findById(id);
		if (persistedEntity == null) {
			log.warn("NotaSpesa [id={}] not found", id);
			throw new EntityNotFoundException("NotaSpesa non esiste");
		}
		
//		controllo che la nota spesa sia nello stato corretto
		if (!STATO_IN_VALIDAZIONE.equals(persistedEntity.getStato())) {
			log.warn("NotaSpesa [id={}] non in stato IN VALIDAZIONE", id);
			throw new ValidateNotaSpesaException("NotaSpesa non in stato IN VALIDAZIONE");
		}
		
//		controllo che l'importo totale sia uguala alla somma degli importi
		Double importoTotale = notaSpesa.getImportoTotale();
		double somma = 0;
		for(VoceSpesa v : notaSpesa.getVociSpesa()) {
			somma += v.getImporto();
		}
		if (importoTotale != somma) {
			log.warn("NotaSpesa [id={}] ha importi non coerenti", id);
			throw new EntityNotFoundException("NotaSpesa ha importi non coerenti");
		}
		
		notaSpesa.setStato(STATO_VALIDA);
		update(persistedEntity, notaSpesa);		
		
		log.info("NotaSpesa [id={}] modificata", id);
	}
	
	public void rifiutaNotaSpesa(int id, NotaSpesa notaSpesa) throws EntityNotFoundException, ValidateNotaSpesaException {
		
		log.info("Rifiuto NotaSpesa [id={}]", id);
		
//		controllo se la nota spesa esiste
		NotaSpesa persistedEntity = this.notaSpesaDao.findById(id);
		if (persistedEntity == null) {
			log.warn("NotaSpesa [id={}] not found", id);
			throw new EntityNotFoundException("NotaSpesa non esiste");
		}
		
//		controllo che la nota spesa sia nello stato corretto
		if (!STATO_IN_VALIDAZIONE.equals(persistedEntity.getStato())) {
			log.warn("NotaSpesa [id={}] non in stato IN VALIDAZIONE", id);
			throw new ValidateNotaSpesaException("NotaSpesa non in stato IN VALIDAZIONE");
		}
		
//		controllo che sia stato specificato almeno un motivo
		if (!notaSpesa.getVociSpesa().stream().anyMatch(v -> !v.getCommento().isEmpty())) {
			log.warn("NotaSpesa [id={}] priva di commento per il rifiuto", id);
			throw new ValidateNotaSpesaException("Nessun commento per il rifiuto");
		}
		
		notaSpesa.setStato(STATO_RIFIUTATA);
		update(persistedEntity, notaSpesa);
		
		log.info("NotaSpesa [id={}] modificata", id);		
	}
	
}
