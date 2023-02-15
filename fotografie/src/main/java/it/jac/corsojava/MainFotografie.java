package it.jac.corsojava;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ArchivioFotografie;
import it.jac.corsojava.entity.Fotografia;
import it.jac.corsojava.entity.FormatoFoto;

public class MainFotografie {

	private static Logger log = LogManager.getLogger(MainFotografie.class);
	
	public static void main(String[] args) {
		
		log.info("Applicazione avviata");
		
		Scanner sc = new Scanner(System.in);
		
		// creo una nuova istanza dell'archivio
		ArchivioFotografie archivio = new ArchivioFotografie();
		
		// dichiaro una variabile di tipo boolean
		boolean esc = false;
		do {
			
			System.out.println("***MENU APPLICAZIONE***");
			System.out.println(" 1) NUOVA FOTOGRAFIA");
			System.out.println(" 2) ELENCO FOTOGRAFIE");
			System.out.println(" 3) ELENCO FOTOGRAFIE MAGGIORE DI 3KB");
			System.out.println(" 4) MODIFICA FOTOGRAFIA");
			System.out.println(" 5) CANCELLA FOTOGRAFIA");
			System.out.println("99) ESCI");
			System.out.println("******");
			
			System.out.print("Scegli operazione: ");
			String scelta = sc.nextLine();
			
			switch(scelta) {
			
				case "1": {
					
					nuovaFotografia(sc, archivio);
					break;
				}
				case "2": {
					
					elencoFotografie(sc, archivio);
					break;
				}
				case "3": {
					
					elencoFotografieMaggiore(sc, archivio);
					break;
				}
				case "4": {
					
					modificaFotografia(sc, archivio);
					break;
				}
				case "5": {
					
					cancellaFotografia(sc, archivio);
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

	private static void nuovaFotografia(Scanner sc, ArchivioFotografie archivio) {
		
		//Creo l'istanza fotografia
		Fotografia fotografia = new Fotografia();
		
		//Riempio man mano con l'input dell'utente
		System.out.println("Inserisci nome fotografia: ");
		fotografia.setNome(sc.nextLine());
		
		System.out.println("Inserisci dimensione fotografia: ");
		fotografia.setDimensione(Double.parseDouble(sc.nextLine()));
		
		setFormatoFoto(sc, fotografia);
		
		setProprietaFoto(sc, fotografia);
		
		//Inserimento della fotografia nell'archivio
		int id = archivio.nuovaFotografia(fotografia);
		
		System.out.println("Fotografia salvata con ID [" + id + "]");

	}

	private static void elencoFotografie(Scanner sc, ArchivioFotografie archivio) {
		
		ArrayList<Fotografia> fotografie = archivio.getListFotografie();
		
		for (Fotografia foto : fotografie) {
			System.out.println(String.format(
					"%5s|%15s|%7s|%14s",
					"ID", "NOME", "FORMATO", "DIMENSIONE(KB)"));
			System.out.println(String.format(
					"%5s|%15s|%7s|%10.2f",
					foto.getId(), foto.getNome(), foto.getFormato(), foto.getDimensione()));
			
			//Creo parte per la visualizzaizone delle proprietà
			System.out.println("-------------------------------------\n"
					+ "Proprietà: ");
			System.out.println(String.format("%15s|%15s", "CHIAVE", "VALORE"));
			for (HashMap.Entry<String, String> set : foto.getProprieta().entrySet()) {
				System.out.println(String.format("%15s|%15s", set.getKey(), set.getValue()));
			}
			System.out.println("-------------------------------------");
		}
	}

	private static void elencoFotografieMaggiore(Scanner sc, ArchivioFotografie archivio) {
		
		ArrayList<Fotografia> fotografie = archivio.getListFotografie();
		
		boolean esiste = false;
		
		for (Fotografia foto : fotografie) {
			if (foto.getDimensione()> 3 ) {
				esiste = true;
				System.out.println(String.format(
						"%5s|%15s|%7s|%14s",
						"ID", "NOME", "FORMATO", "DIMENSIONE(MB)"));
				System.out.println(String.format(
						"%5s|%15s|%7s|%9.3f",
						foto.getId(), foto.getNome(), foto.getFormato(), foto.getDimensione()/1024));
				
				//Creo parte per la visualizzaizone delle proprietà
				System.out.println("-------------------------------------\n"
						+ "Proprietà: ");
				System.out.println(String.format("%15s|%15s", "CHIAVE", "VALORE"));
				for (HashMap.Entry<String, String> set : foto.getProprieta().entrySet()) {
					System.out.println(String.format("%15s|%15s", set.getKey(), set.getValue()));
				}
				System.out.println("-------------------------------------");
			}
		}
		
		if (!esiste) {
			System.out.println("Nessuna foto è di dimensioni maggiori di 3kb!");
		}
		
	}

	private static void modificaFotografia(Scanner sc, ArchivioFotografie archivio) {
		
		System.out.println("Inserisci id foto da modificare: ");
		int id = Integer.parseInt(sc.nextLine());
		
		//Dopo aver preso l'id prendo l'oggetto fotografia
		Fotografia foto = archivio.getFotografia(id);
		
		String scelta = new String();
		
		//Permetto di modificare la foto più volte
		while (scelta!="") {
			System.out.println("Inserisci cosa vuoi modificare: ");
			scelta = sc.nextLine();
			switch (scelta) {
			case "nome":
				System.out.println("Inserisci nome: ");
				foto.setNome(sc.nextLine());
				break;
			case "formato":
				setFormatoFoto(sc, foto);
				break;
			case "dimensione":
				System.out.println("Inserisci dimensione: ");
				foto.setDimensione(Integer.parseInt(sc.nextLine()));
				break;
			case "proprieta":
				setProprietaFoto(sc, foto);
				break;
			}
		}
		
		
	}
	
	private static void cancellaFotografia(Scanner sc, ArchivioFotografie archivio) {
		
		System.out.println("Inserisci id fotografia da cancellare: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if (archivio.cancellaFotografia(id)) {
			System.out.println("Cancellazione effettuata");
		} else {
			System.out.println("Problema nella cancellazione");
		}
	}
	
	//Dichiaro metodo che viene utilizzato più volte
	public static void setProprietaFoto(Scanner sc, Fotografia foto) {
		System.out.println("Inserisci le proprietà della fotografia: ");
		
		//L'utente è libero di tralasciarle
		String chiave = null;
		String valore = null;
		HashMap<String, String> proprieta = new HashMap<>();
		
		do {
			System.out.println("Chiave: ");
			chiave = sc.nextLine();
			System.out.println("Valore: ");
			valore = sc.nextLine();
			if (chiave=="" || valore=="") { //Evito di registrare valori vuoti
				break; 
			} else {
				proprieta.put(chiave, valore);
			}
		} while (chiave!="" || valore!=""); //Se l'utente preme invio senza aver scritto
		
		foto.setProprieta(proprieta);
	}
	
	//Dichiaro metodo che viene utilizzato più volte
	private static void setFormatoFoto(Scanner sc, Fotografia foto) {
		
		System.out.println("Inserisci formato fotografia: ");
		String formatoStr = sc.nextLine();
		
		//Trasformo le stringhe in FormatoFoto
		FormatoFoto formato = null;
		switch (formatoStr) {
		case "jpeg":
			formato=FormatoFoto.JPEG;
			break;
		case "jpg":
			formato=FormatoFoto.JPG;
			break;
		case "webp":
			formato=FormatoFoto.WEBP;
			break;
		case "png":
			formato=FormatoFoto.PNG;
			break;
		case "gif":
			formato=FormatoFoto.GIF;
			break;
		default : //Gestisco un minimo gli errori di input 
			formato=FormatoFoto.JPG;
			break;
		}
		//Inserisco il formato trasformato
		foto.setFormato(formato);
	}

}
