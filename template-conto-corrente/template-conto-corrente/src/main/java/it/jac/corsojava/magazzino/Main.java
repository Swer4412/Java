package it.jac.corsojava.magazzino;

import java.util.Scanner;

/*
Definire le classi necessarie a scrivere un programma in grado di simulare una gestione semplificata 
di un magazzino.

Il magazzino gestisce una lista di prodotti, ognuno dei quali è caratterizzato da:
	- codice
	- descrizione
	- prezzo
	- stato (IN MAGAZZINO, IN TRANSITO, CONSEGNATO)
Le operazioni che possono essere eseguite sono:
	
	- entrata merce (aggiunta di un prodotto al magazzino)
	- spedizione (invio del prodotto a destinazione con conseguente cambio di stato)
	- consegnato (prodotto consegnato al cliente -> stato finale del prodotto)
	- richiesta lista completa prodotti
	
Per semplicità la lista dei prodotti in magazzino rimane anche dopo la spedizione e la consegna, 
viene solo modificato lo stato
*/
public class Main {
	
	public static void main(String[] args) {
		System.out.println("Magazzino");
		String codice;
		Magazzino mgz = new Magazzino();
		Scanner sc = new Scanner(System.in);
		boolean esc = false;
		
		do {
			System.out.println("Scegli una azione:\n"
					+ "1 Inserisci prodotto\n"
					+ "2 Spedisci\n"
					+ "3 Conferma consegna\n"
					+ "4 Lista prodotti\n"
					+ "5 ESCI");
			
			String scelta = sc.nextLine();
			switch (scelta) {
			case "1":
				System.out.println("Inserisci descrizione del prodotto: ");
				String desc = sc.nextLine();
				System.out.println("Inserisci prezzo del prodotto: ");
				String prezzo = sc.nextLine();
				mgz.aggiungiProdotto(desc, prezzo);
				break;
			case "2":
				System.out.println("Inserisci codice prodotto da spedire: ");
				codice = sc.nextLine();
				System.out.println(mgz.spedisci(codice));
				break;
			case "3":
				System.out.println("Inserisci codice prodotto conseganto: ");
				codice = sc.nextLine();
				System.out.println(mgz.consegna(codice));
				break;
			case "4":
				System.out.println(mgz.visualizzaProdotti());
				break;
			case "5":
				esc = true;
			}
			
			
		} while (!esc);
		
	}
}















