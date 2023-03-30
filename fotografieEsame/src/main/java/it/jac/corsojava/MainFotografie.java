package it.jac.corsojava;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.FotografieService;
import it.jac.corsojava.entity.Fotografia;
import it.jac.corsojava.entity.Proprieta;
import it.jac.corsojava.exception.EntityNotFoundException;

public class MainFotografie {

	private static Logger log = LogManager.getLogger(MainFotografie.class);
	
	public static void main(String[] args) {
		
		log.info("Applicazione avviata");
		
		Scanner sc = new Scanner(System.in);
		
		// creo una nuova istanza di service
		FotografieService service = new FotografieService();
		
		boolean esc = false;
		do {
			
			System.out.println("***MENU APPLICAZIONE***");
			System.out.println(" 1) NUOVA FOTOGRAFIA");
			System.out.println(" 2) CERCA FOTO DA ID");
			System.out.println(" 3) CERCA FOTO DA DESCRIZIONE");
			System.out.println(" 4) CANCELLA FOTOGRAFIA");
			System.out.println("99) ESCI");
			System.out.println("******");
			
			System.out.print("Scegli operazione: ");
			String scelta = sc.nextLine();
			
			switch(scelta) {
			
				case "1": {
					
					nuovaFotografia(sc, service);
					break;
				}
				case "2": {
					
					cercaFotoDaId(sc, service);
					break;
				}
				case "3": {
					
					cercaFotoConNome(sc, service);
					break;
				}
				case "4": {
					
					cancellaFotografia(sc, service);
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

	private static void nuovaFotografia(Scanner sc, FotografieService service) {
		
		//Creo l'istanza fotografia
		Fotografia fotografia = new Fotografia();
		
		//Riempio man mano con l'input dell'utente
		System.out.println("Inserisci nome fotografia: ");
		fotografia.setNome(sc.nextLine());
		
		System.out.println("Inserisci dimensione fotografia: ");
		fotografia.setDimensione(Double.parseDouble(sc.nextLine()));
		
		//Gestisco il formato foto 
		String formato = null;
		while (formato == null) {
			System.out.println("Inserisci formato foto: ");
			Map<String, String> mapFormato = new LinkedHashMap<>();
			mapFormato.put("A", service.FORMATO_JPG);
			mapFormato.put("B", service.FORMATO_JPEG);
			mapFormato.put("C", service.FORMATO_PNG);
			mapFormato.put("D", service.FORMATO_GIF);
			mapFormato.put("E", service.FORMATO_WEBP);
	
			Set<Entry<String, String>> entrySet = mapFormato.entrySet();
			for(Entry<String, String> entry : entrySet) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
			
			System.out.print("Seleziona lettera: ");
			String lettera = sc.nextLine();
			//.toUpperCase in modo che posso inserire anche "a" invece che "A"
			formato = mapFormato.get(lettera.toUpperCase());
		}
		//Una volta che il formato è stato selezionato correttamente, lo inserisco
		fotografia.setFormato(formato);
		
		
		setProprietaFoto(sc, fotografia);
		
		//Inserimento della fotografia nell'archivio
		service.create(fotografia);

	}

	private static void cercaFotoDaId(Scanner sc, FotografieService service) {
		
		System.out.print("Inserisci ID: ");
		String idStr = sc.nextLine();
		int id = 0;
		
		try {
			id = Integer.parseInt(idStr);
			Fotografia foto = service.findFotografiaById(id);

			System.out.println(String.format(
					"%5s|%15s|%7s|%14s|%17s|%14",
					"ID", "NOME", "FORMATO", "DIMENSIONE(KB)", "UTENTECREAZIONE", "DATACREAZIONE"));
			System.out.println(String.format(
					"%5s|%15s|%7s|%10.2f|%17s|%14",
					foto.getId(), foto.getNome(), foto.getFormato(), foto.getDimensione()));
			
			//Creo parte per la visualizzaizone delle proprietà
			System.out.println("-------------------------------------\n"
					+ "Proprietà: ");
			System.out.println(String.format("%15s|%15s", "CHIAVE", "VALORE"));
			for (Proprieta prop : foto.getProprieta()) {
				System.out.println(String.format("%15s|%15s", prop.getChiave(), prop.getValore()));
			}
			System.out.println("-------------------------------------");
			
		} catch (NumberFormatException e) {
			System.out.println("Id non valido");
		} 
	}
	
	private static void cercaFotoConNome(Scanner sc, FotografieService service) {
		
		System.out.print("Inserisci Nome: ");
		String nome = sc.nextLine();
		
		try {
			ArrayList<Fotografia> fotografie = service.findFotografieByNome(nome);
			
			for (Fotografia foto: fotografie) {
				System.out.println(String.format(
						"%5s|%15s|%7s|%14s|%17s|%14",
						"ID", "NOME", "FORMATO", "DIMENSIONE(KB)", "UTENTECREAZIONE", "DATACREAZIONE"));
				System.out.println(String.format(
						"%5s|%15s|%7s|%10.2f|%17s|%14",
						foto.getId(), foto.getNome(), foto.getFormato(), foto.getDimensione()));
				
				//Creo parte per la visualizzaizone delle proprietà
				System.out.println("-------------------------------------\n"
						+ "Proprietà: ");
				System.out.println(String.format("%15s|%15s", "CHIAVE", "VALORE"));
				for (Proprieta prop : foto.getProprieta()) {
					System.out.println(String.format("%15s|%15s", prop.getChiave(), prop.getValore()));
				}
				System.out.println("-------------------------------------");
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Id non valido");
		} 
	}

	
	private static void cancellaFotografia(Scanner sc, FotografieService service) {
		
		System.out.print("Inserisci ID: ");
		String id = sc.nextLine();
		
		try {
			service.delete(Integer.parseInt(id));
			//Scrivo che è stata eliminata la fotografia subito sotto perchè se c'è un problema, faccio catch
			System.out.println("Fotografia eliminata!");
			
		} catch (NumberFormatException e) {
			
			System.out.println("ID non valido");
		} catch (EntityNotFoundException e) {
			
			System.out.println(e.getMessage());
			return;
		}
	}
	
	//Dichiaro metodo che viene utilizzato più volte
	public static void setProprietaFoto(Scanner sc, Fotografia foto) {
		System.out.println("Inserisci le proprietà della fotografia: ");
		
		String chiave = null;
		String valore = null;
		boolean termina = false;
		
		ArrayList<Proprieta> proprietaList = new ArrayList<>();
		//Loop che crea oggetti proprietà e li inserisce nella lista delle proprietà
		do {
			Proprieta proprieta = new Proprieta();
			System.out.println("Chiave: ");
			proprieta.setChiave(sc.nextLine());
			System.out.println("Valore: ");
			proprieta.setValore(sc.nextLine());
			System.out.println("Inserire ulteriori proprietà?(Y/N): ");
			termina = "n".equalsIgnoreCase(sc.nextLine());
			
			proprietaList.add(proprieta);
			
		} while (!termina);
		//Una volta riempita la lista, la inserisco dentro l'oggetto fotografia
		foto.setProprieta(proprietaList);
		
	}

}
