package it.jac.corsojava.magazzino;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.MainContoCorrente;

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
	
	private static Logger log = LogManager.getLogger(Main.class);
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		log.info("Applicazione avviata"); //Aggiungo i log perchè di sii
		
		System.out.println("Magazzino");
		
		MagazzinoInterfaccia mgz = new Magazzino();
		
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
				inserisciProdotto(mgz);
				break;
			case "2":
				spedisciProdotto(mgz);
				break;
			case "3":
				consegnaProdotto(mgz);
				break;
			case "4":
				visualizzaProdotti(mgz);
				break;
			case "5":
				log.info("Uscita dal programma");
				esc = true;
			}
			
			
		} while (!esc);
		log.info("Applicazione terminata");
	}
	
	public static void inserisciProdotto(MagazzinoInterfaccia mag) {
		log.info("Inserimento prodotto");
		System.out.println("Inserisci descrizione del prodotto: ");
		String desc = sc.nextLine();
		System.out.println("Inserisci prezzo del prodotto: ");
		String prezzo = sc.nextLine();
		mag.aggiungiProdotto(desc, prezzo);
	}
	
	public static void spedisciProdotto(MagazzinoInterfaccia mag) {
		String codice;
		log.info("Spedizione prodotto");
		System.out.println("Inserisci codice prodotto da spedire: ");
		codice = sc.nextLine();
		System.out.println(mag.cambiaStato(codice, "IN SPEDIZIONE"));
	}
	
	public static void consegnaProdotto(MagazzinoInterfaccia mag) {
		String codice;
		log.info("Consegna prodotto");
		System.out.println("Inserisci codice prodotto conseganto: ");
		codice = sc.nextLine();
		System.out.println(mag.cambiaStato(codice, "CONSEGNATO"));
	}
	
	public static void visualizzaProdotti(MagazzinoInterfaccia mag) {
		log.info("Visualizzazione prodotti");
		System.out.println(mag.visualizzaProdotti());
	}
}















