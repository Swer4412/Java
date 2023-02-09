package it.jac.corsojava;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ArchivioCrociera;
import it.jac.corsojava.entity.Prenotazione;
import it.jac.corsojava.entity.Persona;
import it.jac.corsojava.entity.TipoCabina;

// classe che interagisce con l'utente
// contiene le funzioni richieste dall'utente
// richiama le funzioni di archiviazione
public class MainCrociera {

	private static Logger log = LogManager.getLogger(MainCrociera.class);
	
	public static void main(String[] args) {
		
		log.info("Applicazione avviata");
		
		Scanner sc = new Scanner(System.in);
		
		// creo una nuova istanza dell'archivio
		ArchivioCrociera archivio = new ArchivioCrociera();
		
		// dichiaro una variabile di tipo boolean
		boolean esc = false;
		do {
			
			System.out.println("***MENU APPLICAZIONE***");
			System.out.println(" 1) NUOVA PRENOTAZIONE");
			System.out.println(" 2) CALCOLA PRENOTAZIONE");
			System.out.println(" 3) ELENCO PRENOTAZIONI");
			System.out.println(" 4) ELENCO PRENOTAZIONI CON DURATA MAGGIORE DI");
			System.out.println(" 5) CANCELLA PRENOTAZIONE");
			System.out.println("99) ESCI");
			System.out.println("******");
			
			System.out.print("Scegli operazione: ");
			String scelta = sc.nextLine();
			
			switch(scelta) {
			
				case "1": {
					
					nuovaPrenotazione(sc, archivio);
					break;
				}
				case "2": {
					
					calcolaPrenotazione(sc, archivio);
					break;
				}
				case "3": {
					
					elencoPrenotazioni(sc, archivio);
					break;
				}
				case "4": {
					
					elencoPrenotazioniMaggiore(sc, archivio);
					break;
				}
				case "5": {
					
					cancellaPrenotazione(sc, archivio);
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

	private static void nuovaPrenotazione(Scanner sc, ArchivioCrociera archivio) {
		
		//Riempio l'oggetto prenotazione
		Prenotazione prenotazione = new Prenotazione();
		
		System.out.print("Inserisci la durata del viaggio: ");
		prenotazione.setDurataViaggio(Integer.parseInt(sc.nextLine()));
		
		System.out.print("Inserisci la tipologia di cabina: ");
		prenotazione.setCabina(sc.nextLine());
		
		System.out.print("Inserisci numero di persone: ");
		int num = Integer.parseInt(sc.nextLine());
		ArrayList<Persona> pList = new ArrayList<Persona>();
		
		//Riempio l'istanza di persona
		for (int i = 0; i<num; i++) {
			System.out.println("Inserisci nome " + (i+1) + "° persona: ");
			String nome = sc.nextLine();
			System.out.println("Inserisci cognome " + (i+1) + "° persona: ");
			String cognome = sc.nextLine();
			System.out.println("Inserisci eta " + (i+1) + "° persona: ");
			int eta = Integer.parseInt(sc.nextLine());
			
			Persona p = new Persona(nome, cognome, eta);
			pList.add(p);
		}
		//La lista di persone viene passata a prenotazione
		prenotazione.setPersone(pList);	
		
		int idGenerato = archivio.nuovaPrenotazione(prenotazione);
		
		System.out.println("Prodotto archiviato con ID [" + idGenerato + "]");

	}

	private static void calcolaPrenotazione(Scanner sc, ArchivioCrociera archivio) {
		
		System.out.println("Inserisci id prenotazione da calcolare: ");
		int id = Integer.parseInt(sc.nextLine());
		
		System.out.println("Prezzo totale della prenotazione " + id + ": " + archivio.calcolaPrenotazione(id));
	}

	private static void elencoPrenotazioni(Scanner sc, ArchivioCrociera archivio) {
		
		ArrayList<Prenotazione> prenotazioni = archivio.getListPrenotazioni();
		
		System.out.println(String.format("%5s|%15s|%12s", "ID", "DURATA VIAGGIO", "CABINA"));
		for (Prenotazione pre : prenotazioni) {
			System.out.println(String.format("%5s|%15s|%12s", pre.getId(), pre.getDurataViaggio(), pre.getCabina()));
			
			//Creo parte per la visualizzaizone delle persone
			System.out.println("--------------------------------------");
			System.out.println(String.format("%12s|%12s|%5s", "NOME", "COGNOME", "ETA'"));
			for (Persona pers : pre.getPersone()) {
				System.out.println(String.format("%12s|%12s|%5s", pers.getNome(), pers.getCognome(), pers.getEta()));
			}
		}
	}

	private static void elencoPrenotazioniMaggiore(Scanner sc, ArchivioCrociera archivio) {
		ArrayList<Prenotazione> prenotazioni = archivio.getListPrenotazioni();
		
		System.out.println("Inserisci durata minima: ");
		int durata = Integer.parseInt(sc.nextLine());
		
		System.out.println(String.format("%5s|%15s|%12s", "ID", "DURATA VIAGGIO", "CABINA"));
		for (Prenotazione pre : prenotazioni) {
			//Guardo per ogni prenotazione la durata 
			if (pre.getDurataViaggio() >= durata) {
				System.out.println(String.format("%5s|%15s|%12s", pre.getId(), pre.getDurataViaggio(), pre.getCabina()));
				
				//Creo parte per la visualizzaizone delle persone
				System.out.println("--------------------------------------");
				System.out.println(String.format("%12s|%12s|%5s", "NOME", "COGNOME", "ETA'"));
				for (Persona pers : pre.getPersone()) {
					System.out.println(String.format("%12s|%12s|%5s", pers.getNome(), pers.getCognome(), pers.getEta()));
				}
			} else {
				System.out.println("Nessuna prenotazione dura più di " + durata + " giorni");
			}
		}
	}
	
	private static void cancellaPrenotazione(Scanner sc, ArchivioCrociera archivio) {

	}

}
