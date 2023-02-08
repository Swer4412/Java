package it.jac.corsojava;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ArchivioMagazzino;
import it.jac.corsojava.entity.Prodotto;
import it.jac.corsojava.entity.StatoProdotto;

// classe che interagisce con l'utente
// contiene le funzioni richieste dall'utente
// richiama le funzioni di archiviazione
public class MainMagazzino {

	private static Logger log = LogManager.getLogger(MainMagazzino.class);
	
	public static void main(String[] args) {
		
		log.info("Applicazione avviata");
		
		Scanner scanner = new Scanner(System.in);
		
		// creo una nuova istanza dell'archivio
		ArchivioMagazzino archivio = new ArchivioMagazzino();
		
		// dichiaro una variabile di tipo boolean
		boolean esc = false;
		do {
			
			System.out.println("***MENU APPLICAZIONE***");
			System.out.println(" 1) ENTRATA MERCE");
			System.out.println(" 2) SPEDIZIONE");
			System.out.println(" 3) CONSEGNA PRODOTTO");
			System.out.println(" 4) ELENCO PRODOTTI");
			System.out.println("99) ESCI");
			System.out.println("******");
			
			System.out.print("Scegli operazione: ");
			String scelta = scanner.nextLine();
			
			switch(scelta) {
			
			case "1": {
				
				entrataMerce(scanner, archivio);
				break;
			}
			case "2": {
				
				spedizione(scanner, archivio);
				break;
			}
			case "3": {
				
				consegnaProdotto(scanner, archivio);
				break;
			}
			case "4": {
				
				elencoProdotti(scanner, archivio);
				break;
			}
			case "99": {
				
				esc = true;
				break;
			}
			default : {
				System.out.println("Scelta non valida. Ripeti");
			}
			}
			
		} while(!esc);
		
		
		log.info("Applicazione terminata");
	}

	private static void entrataMerce(Scanner scanner, ArchivioMagazzino archivio) {
		
//		log.info("richiesta registrazione nuova entrata merce");
		
		// creo una nuova istanza di "Prodotto" per poter impostare al suo interno i dati raccolti dall'utente
		Prodotto prodotto = new Prodotto();
		
		System.out.print("Inserisci il codice: ");
		prodotto.setCod(scanner.nextLine());
		
		System.out.print("Inserisci la descrizione: ");
		prodotto.setDescrizione(scanner.nextLine());

		System.out.print("Inserisci il prezzo: ");
		// leggo da tastiera il prezzo e lo converto in valore double
		// ATTENZIONE: questa operazione potrebbe generare un errore...ma al momento non sappiamo come gestirla!
		// inserire quindi un valore corretto
		prodotto.setPrezzo(Double.parseDouble(scanner.nextLine()));
		
//		log.debug("cerco di aggiungere il prodotto all'archivio {}", prodotto);
		// raccolti i dati dall'utente richiamo la funzione di archiviazione
		int idGenerato = archivio.entrataMerce(prodotto);
		
		System.out.println("Prodotto archiviato con ID [" + idGenerato + "]");
//		log.info("Prodotto salvato {}", idGenerato);
	}

	private static void spedizione(Scanner scanner, ArchivioMagazzino archivio) {
		
//		log.info("richiesta spedizione prodotto");
		
		System.out.print("Inserisci ID del prodotto: ");
		int id = Integer.parseInt(scanner.nextLine());
		
//		log.debug("richiamo la funzione dell'archivio con parametro {}", id);
		boolean spedito = archivio.spedisci(id);
		if (!spedito) {
			System.out.println("Non è stato possibile spedire il prodotto");
//			log.warn("spedizione non possibile per il prodotto con id {}", id);
		} else {
			System.out.println("Prodotto spedito");
//			log.info("prodotto spedito {}", prodotto);
		}
		
	}

	private static void consegnaProdotto(Scanner scanner, ArchivioMagazzino archivio) {
		
//		log.info("richiesta consegna prodotto");
		
		System.out.print("Inserisci ID del prodotto: ");
		int id = Integer.parseInt(scanner.nextLine());
		
//		log.debug("richiamo la funzione dell'archivio con parametro {}", id);
		boolean consegnato = archivio.consegnato(id);
		if (!consegnato) {
			System.out.println("Non è stato possibile consegnare il prodotto");
//			log.warn("consegna non possibile per il prodotto con id {}", id);
		} else {
			System.out.println("Prodotto consegnato");
//			log.info("prodotto consegnato {}", prodotto);
		}

	}

	private static void elencoProdotti(Scanner scanner, ArchivioMagazzino archivio) {

//		log.info("richiesto elenco prodotti");
		
		System.out.println("------ELENCO PRODOTTI------");
		
		ArrayList<Prodotto> list = archivio.getListProdotti();
//		log.debug("restituiti {} prodotti", list.size());
		
		// questa è la forma più semplice di stampa dell'oggetto
		for (Prodotto prodotto : list) {
			
			System.out.println(prodotto);
		}
		System.out.println("--------------------");
		
		// in alternativa è possibile estrarre tutti gli attributi e stamparli uno per uno
		for (Prodotto prodotto : list) {
		
			System.out.print(prodotto.getId());
			System.out.print("|");
			System.out.print(prodotto.getCod());
			System.out.print("|");
			System.out.print(prodotto.getDescrizione());
			System.out.print("|");
			System.out.println(prodotto.getPrezzo());
			System.out.print("|");
			System.out.println(prodotto.getStato());
		}
		System.out.println("--------------------");
		
		// se curo di più la stampa posso anche formattarla
		System.out.println(String.format("%6s|%10s|%20s|%10s|%10s", "ID", "COD", "DESCR.", "PREZZO", "STATO"));
		for (Prodotto prodotto : list) {
			
			System.out.println( //%6d: "%6" = 6 caretteri; "d" = di tipo double
					String.format("%6d|%10s|%20s|%10.2f|%10s", //10.2f: "%10.2" = 10 caratteri per il numero intero, 2 dopo la virgola; f = float
							prodotto.getId(), 
							prodotto.getCod(), 
							prodotto.getDescrizione(),
							prodotto.getPrezzo(),
							prodotto.getStato().toString()));
		}

	}

}
