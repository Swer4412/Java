package it.jac.corsojava.magazzino;

import java.util.ArrayList;

public class Magazzino {
	
	ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
	
	public void aggiungiProdotto(String desc, String prezzo) {
		double prz = Double.parseDouble(prezzo); //Come Integer.parseInt
		int codice = nextCodice();
		this.prodotti.add(new Prodotto(desc, prz, codice));
	}
	
	public int nextCodice() {
		int codice;
		
		try {
			Prodotto prod = prodotti.get(-1); //La prima volta prodotti di -1 non esiste perchè è vuota
			codice = prod.getCodice() + 1;
			return codice;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public String spedisci(String codice) {
		String msg = "Errore nel codice";
		int cod;
		//Guardo se il codice inserito è una stringa altrimenti ritorna problema 
		try {
			cod = Integer.parseInt(codice);
		} catch (Exception e) {
			return msg;
		}
		//Guardo quale prodotto ha il codice inserito
		for (Prodotto prod : prodotti) {
			if (prod.getCodice()==cod) {
				prod.setStato("IN SPEDIZIONE");
				msg = "Prodotto spedito";
			} else {
				return msg;
			}
		}
		return msg;
	
	}
	
	public String consegna(String codice) {
		String msg = "Errore nel codice";
		int cod;
		//Guardo se il codice inserito è una stringa altrimenti ritorna problema 
		try {
			cod = Integer.parseInt(codice);
		} catch (Exception e) {
			return msg;
		}
		//Guardo quale prodotto ha il codice inserito
		for (Prodotto prod : prodotti) {
			if (prod.getCodice()==cod) {
				prod.setStato("CONSEGNATO");
				msg = "Prodotto consegnato";
			} else {
				return msg;
			}
		}
		return msg;
	
	}
	
	public String visualizzaProdotti() {
		String stringa = "";
		for (Prodotto prod : prodotti) {
			stringa = stringa 
					+ "Codice: " +prod.getCodice() 
					+ "\nDescrizione: " + prod.getDesc()
					+ "\nPrezzo: " + prod.getPrezzo()
					+ "\nStato: " + prod.getStato()
					+ "\n------------------\n";
		}
		return stringa;
	}
}
