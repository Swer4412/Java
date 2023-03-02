package it.jac.corsojava;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ServiceNoteSpesa;
import it.jac.corsojava.entity.Entity;
import it.jac.corsojava.entity.NotaSpesa;
import it.jac.corsojava.entity.StatoSpesa;
import it.jac.corsojava.entity.VoceSpesa;

public class MainNoteSpesa {

	private static Logger log = LogManager.getLogger(MainNoteSpesa.class);

	public static void main(String[] args) {
		
		log.info("Applicazione avviata");

		Scanner sc = new Scanner(System.in);
		
		ServiceNoteSpesa service = new ServiceNoteSpesa();
		
		boolean esc = false;
		
		System.out.println("NOTA SPESE");
		
		do {
			System.out.println("SCEGLI UNA AZIONE:\n"
					+ "1: Inserisci nota spese\n"
					+ "2: Elimina nota spese\n"
					+ "3: Visualizza note da validare\n"
					+ "4: Valida nota spese\n"
					+ "5: Visualizza note spesa di stato\n"
					+ "99: Esci");
			
			String scelta = sc.nextLine();
			switch (scelta) {
			case "1":
				inserisciNotaSpese(sc, service);
				break;
			case "2":
				eliminaNotaSpese(sc, service);
				break;
			case "3":
				visualizzaNoteValidare(sc, service);
				break;
			case "4":
				validaNota(sc, service);
				break;
			case "5":
				visualizzaNoteDiStato(sc, service);
				break;
			case "99":
				esc = true;
				break;
			}
			
		} while (!esc);
		log.info("Applicazione terminata");
		
	}

	private static void inserisciNotaSpese(Scanner sc, ServiceNoteSpesa service) {
		
		//Creo istanze
		Entity entity = new Entity();
		NotaSpesa notaSpesa = new NotaSpesa();
		ArrayList<VoceSpesa> voci = new ArrayList<>();
		
		//Riempio con input dell utente
		System.out.println("Inserisci il tuo id: ");
		notaSpesa.setId_dipendente(Integer.parseInt(sc.nextLine()));
		
		System.out.println("Inserisci codice nota spesa: ");
		notaSpesa.setCodice(sc.nextLine());
		
		System.out.println("Inserisci mese di riferimento (aaaamm): ");
		notaSpesa.setMese_rif(sc.nextLine());
		
		String str = null;
		
		//Riempio la lista delle voci spesa 
		do {
			
			//Questa istanza di vocespesa viene poi inserita dentro la lista di tipo VoceSpesa
			VoceSpesa voceSpesa = new VoceSpesa();
			
			System.out.println("Inserisci commento voce spesa: ");
			voceSpesa.setCommento(sc.nextLine());
			
			System.out.println("Inserisci importo voce spesa: ");
			voceSpesa.setImporto(Integer.parseInt(sc.nextLine()));
			
			voci.add(voceSpesa);
			
		} while (str !="");		
		
		entity.setNota_spesa(notaSpesa);
		entity.setVoci_spesa(voci);
		
		if (service.registra(entity)) {
			System.out.println("Inserimento avvenuto con successo");
		} else {
			System.out.println("Problema nell'inserimento");
		}
	}

	private static void eliminaNotaSpese(Scanner sc, ServiceNoteSpesa service) {
		
		System.out.println("Inserisci l'id della nota spesa da eliminare: ");
		int id =Integer.parseInt(sc.nextLine());
		
		if (service.elimina(id)) {
			System.out.println("Eliminazione avvenuta con successo");
		} else {
			System.out.println("Problema nell'eliminazione");
		}
		
	}

	private static void visualizzaNoteValidare(Scanner sc, ServiceNoteSpesa service) {
		
		for (Entity entity : service.visualizza()) {
			if (entity.getNota_spesa().getStato().equals(StatoSpesa.REGISTRATA));
			
		}
		
	}

	private static void validaNota(Scanner sc, ServiceNoteSpesa service) {
		// TODO Auto-generated method stub
		
	}

	private static void visualizzaNoteDiStato(Scanner sc, ServiceNoteSpesa service) {
		
		System.out.println("Inserisci lo stato che vuoi visualizzare: ");
		String stato = sc.nextLine();
		//Traduci la stringa in StatoSpesa
		
		for (Entity entity : service.visualizza()) {
			if (entity.getNota_spesa().getStato().equals()); 
		}
		
	}

}