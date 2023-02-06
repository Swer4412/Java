package it.jac.corsojava.magazzino;

import java.util.ArrayList;

public class Magazzino implements MagazzinoInterfaccia {
	
	ArrayList<Prodotto> prodotti = new ArrayList<Prodotto>();
	
	public void aggiungiProdotto(String desc, String prezzo) {
		double prz = Double.parseDouble(prezzo); //Come Integer.parseInt
		int codice = nextCodice();
		this.prodotti.add(new Prodotto(desc, prz, codice));
	}
	
	public String cambiaStato(String codice, String stato) {
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
				prod.setStato(stato);
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
	
	public int nextCodice() {
		int codice;
		
		try {
			Prodotto prod = prodotti.get(prodotti.size()-1); //La prima volta prodotti di -1 non esiste perchè è vuota
			codice = prod.getCodice() + 1;
			return codice;
		} catch (Exception e) {
			return 0;
		}
	}
}
