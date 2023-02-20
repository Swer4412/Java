package it.jac.corsojava.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Prodotto;
import it.jac.corsojava.entity.StatoProdotto;

// rappresenta il gestore del nostro archivio
public class ServiceMagazzino {

	private static Logger log = LogManager.getLogger(ServiceMagazzino.class);

	private MagazzinoDao dao = new MagazzinoDao();

	public int entrataMerce(Prodotto entity) {

		// imposto lo stato iniziale del prodotto
		entity.setStato(StatoProdotto.IN_MAGAZZINO);
		entity.setDataCreazione(LocalDateTime.now());
		entity.setUtenteCreazione("java");
		dao.create(entity);

		return dao.findAllProdotti().get(dao.findAllProdotti().size() - 1).getId();
	}

	public boolean spedisci(int id) {

		Prodotto prodotto = null;

		// ricerco il prodotto con l'ID indicato
		for (Prodotto p : this.dao.findAllProdotti()) {

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
			if (prodotto.getStato() == StatoProdotto.IN_MAGAZZINO) {

				prodotto.setStato(StatoProdotto.IN_TRANSITO);
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

		Prodotto prodotto = null;

		// ricerco il prodotto con l'ID indicato
		for (Prodotto p : this.dao.findAllProdotti()) {

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
			if (prodotto.getStato() == StatoProdotto.IN_TRANSITO) {

				prodotto.setStato(StatoProdotto.CONSEGNATO);
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

	public List<Prodotto> getListProdotti() {

		// restituisco l'elenco di prodotti presenti in archivio
		return this.dao.findAllProdotti();
	}

}
