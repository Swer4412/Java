package it.jac.corsojava.dao;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Prodotto;
import it.jac.corsojava.entity.StatoProdotto;

// rappresenta il gestore del nostro archivio
public class ArchivioMagazzino {

	private static Logger log = LogManager.getLogger(ArchivioMagazzino.class);
	
	// SEQUENZA usata per generare gli ID univoci da assegnare ai prodotti
	private static int SEQ = 11;
	
	// archivio dei prodotti in magazzino
	private ArrayList<Prodotto> listProdotti = new ArrayList<>(); //Non farlo statico
	
	public int entrataMerce(Prodotto entity) {
		
		// assegno all'ID il primo numero di sequenza disponibile
		entity.setId(SEQ++);
		// imposto lo stato iniziale del prodotto
		entity.setStato(StatoProdotto.IN_MAGAZZINO);
		this.listProdotti.add(entity);
		
		return entity.getId();
	}
	
	public boolean spedisci(int id) {
		
		Prodotto prodotto = null;
		
		// ricerco il prodotto con l'ID indicato
		for(Prodotto p : this.listProdotti) {
			
			if (p.getId() == id) {
				
				// ho trovato il prodotto, lo memorizzo in una variabile esterna al ciclo ed termino di iterare
				prodotto = p;
				break;
			}
		}
		
		// se ho trovato il prodotto cerco di modificare lo stato
		if (prodotto != null) {
			
			// se il prodotto è nello stato corretto modifico, altrimenti devo indicare che non è possibile
			if (prodotto.getStato() == StatoProdotto.IN_MAGAZZINO) {
				
				prodotto.setStato(StatoProdotto.IN_TRANSITO);
				log.info("Prodotto aggiornato con stato IN_TRANSITO {}", prodotto);
				return true;
			}
			
			// inserisco nel log il dettaglio dello stato errato
			log.warn("Stato del prodotto errato {}", prodotto);
			
		} else {
			
			// inserisco nel log che il prodotto non è stato trovato con l'ID specificato
			log.warn("Non e' stato trovato il prodotto con ID {}", id);
			
		}
		return false;
	}
	
	public boolean consegnato(int id) {
		
		Prodotto prodotto = null;
		
		// ricerco il prodotto con l'ID indicato
		for(Prodotto p : this.listProdotti) {
			
			if (p.getId() == id) {
				
				// ho trovato il prodotto, lo memorizzo in una variabile esterna al ciclo ed termino di iterare
				prodotto = p;
				break;
			}
		}
		
		// se ho trovato il prodotto cerco di modificare lo stato
		if (prodotto != null) {
			
			// se il prodotto è nello stato corretto modifico, altrimenti devo indicare che non è possibile
			if (prodotto.getStato() == StatoProdotto.IN_TRANSITO) {
				
				prodotto.setStato(StatoProdotto.CONSEGNATO);
				log.info("Prodotto aggiornato con stato CONSEGNATO {}", prodotto);
				return true;
			}
			
			// inserisco nel log il dettaglio dello stato errato
			log.warn("Stato del prodotto errato {}", prodotto);
			
		} else {
			
			// inserisco nel log che il prodotto non è stato trovato con l'ID specificato
			log.warn("Non e' stato trovato il prodotto con ID {}", id);
			
		}
		return false;
		
	}
	
	public ArrayList<Prodotto> getListProdotti() {
		
		// restituisco l'elenco di prodotti presenti in archivio
		return listProdotti;
	}
	
	
}
