package it.jac.corsojava.dao;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Prenotazione;
import it.jac.corsojava.entity.TipoCabina;

// rappresenta il gestore del nostro archivio
public class ArchivioCrociera {

	private static Logger log = LogManager.getLogger(ArchivioCrociera.class);
	
	// SEQUENZA usata per generare gli ID univoci da assegnare ai prodotti
	private static int SEQ = 0;
	
	// archivio dei prodotti in magazzino
	private ArrayList<Prenotazione> listPrenotazioni = new ArrayList<>(); //Non farlo statico
	
	public int nuovaPrenotazione(Prenotazione pren) {
		//Id generato automaticamente
		pren.setId(SEQ++);
		
		this.listPrenotazioni.add(pren);
		
		return pren.getId();
	}
	
	public double calcolaPrenotazione(int id) {
		double tot = 0;
		
		return tot;
	}
	
	public ArrayList<Prenotazione> getListProdotti() {
		
		// restituisco l'elenco di prodotti presenti in archivio
		return listProdotti;
	}
	
	
}
