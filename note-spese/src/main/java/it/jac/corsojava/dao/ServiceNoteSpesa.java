package it.jac.corsojava.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.VoceSpesa;
import it.jac.corsojava.entity.StatoSpesa;

// rappresenta il gestore del nostro archivio
public class ServiceNoteSpesa {

	private static Logger log = LogManager.getLogger(ServiceNoteSpesa.class);

	private NoteSpesaDao dao = new NoteSpesaDao();

	public int registraSpesa(VoceSpesa entity) {
		dao.create(entity);

		return dao.read().get(dao.read().size() - 1).getId();
	}

	public boolean spedisci(int id) {

		VoceSpesa prodotto = null;

		// ricerco il prodotto con l'ID indicato
		for (VoceSpesa p : this.dao.read()) {

			if (p.getId() == id) {

				// ho trovato il prodotto, lo memorizzo in una variabile esterna al ciclo ed
				// termino di iterare
				prodotto = p;
				break;
			}
		}

		// se ho trovato il prodotto cerco di modificare lo stato
		if (prodotto != null) {

			// se il prodotto è nello stato corretto modifico, altrimenti devo indicare che
			// non è possibile
			if (prodotto.getStato() == StatoSpesa.IN_MAGAZZINO) {

				prodotto.setStato(StatoSpesa.IN_TRANSITO);
				log.info("Prodotto aggiornato con stato IN_TRANSITO {}", prodotto);

				this.dao.update(prodotto);
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

		VoceSpesa prodotto = null;

		// ricerco il prodotto con l'ID indicato
		for (VoceSpesa p : this.dao.findAllProdotti()) {

			if (p.getId() == id) {

				// ho trovato il prodotto, lo memorizzo in una variabile esterna al ciclo ed
				// termino di iterare
				prodotto = p;
				break;
			}
		}

		// se ho trovato il prodotto cerco di modificare lo stato
		if (prodotto != null) {

			// se il prodotto è nello stato corretto modifico, altrimenti devo indicare che
			// non è possibile
			if (prodotto.getStato() == StatoSpesa.IN_TRANSITO) {

				prodotto.setStato(StatoSpesa.CONSEGNATO);
				log.info("Prodotto aggiornato con stato CONSEGNATO {}", prodotto);

				this.dao.update(prodotto);
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

	public List<VoceSpesa> getListProdotti() {

		// restituisco l'elenco di prodotti presenti in archivio
		return this.dao.findAllProdotti();
	}

}
