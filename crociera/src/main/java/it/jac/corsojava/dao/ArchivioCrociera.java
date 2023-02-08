package it.jac.corsojava.dao;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Prenotazione;
import it.jac.corsojava.entity.TipoCabina;

//Questa classe serve per la gestione dell arraylist: riempimento, interrogazione

public class ArchivioCrociera {

	private static Logger log = LogManager.getLogger(ArchivioCrociera.class);
	
	private static int SEQ = 0;
	
	private ArrayList<Prenotazione> listPrenotazioni = new ArrayList<>(); //Non farlo statico
	
	public int nuovaPrenotazione(Prenotazione pren) {
		//Id generato automaticamente
		pren.setId(SEQ++);
		
		this.listPrenotazioni.add(pren);
		
		return pren.getId();
	}
	
	public double calcolaPrenotazione(int id) {
		double tot = 0;
		
		//Trovo la prenotazione in base all id
		for (Prenotazione p : listPrenotazioni) {
			if (p.getId() == id) {
				tot = p.getDurataViaggio()*
						p.getPersone().size()*
						getPrezzo(p.getCabina());
			}
		}
		return tot;
	}
	
	//Privata perchè mi serve in un metodo interno
	private double getPrezzo(TipoCabina cabina) {
		double prezzo = 0;
		switch (cabina) {
		case INTERNA:
			prezzo = 80;
		case ESTERNA:
			prezzo = 100;
		case CON_BALCONE:
			prezzo = 150;
		case SUITE:
			prezzo = 200;
		}
		return prezzo;
	
	}
	
	public ArrayList<Prenotazione> getListPrenotazioni() {
		
		return this.listPrenotazioni;
	}
	
	
}
