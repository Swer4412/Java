package it.jac.corsojava;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ServiceNoteSpesa;
import it.jac.corsojava.entity.CategoriaSpesa;
import it.jac.corsojava.entity.Dipendente;
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
		
		Dipendente dipendente = new Dipendente();
		NotaSpesa notaSpesa = new NotaSpesa();
		ArrayList<VoceSpesa> voci = new ArrayList<>();
		CategoriaSpesa categoria = new CategoriaSpesa();
		
		//Riempio con input dell utente
		System.out.println("Inserisci il tuo id: ");
		dipendente.setMatricola(sc.nextLine());
		
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
			voceSpesa.setImporto(Double.parseDouble(sc.nextLine()));
			
			System.out.println("Inserisci l'id categoria voce spesa: ");
			categoria.setDescrizione(sc.nextLine());
			
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
		
		
		
	}

	private static void visualizzaNoteDiStato(Scanner sc, ServiceNoteSpesa service) {
		
		System.out.println("Inserisci lo stato che vuoi visualizzare: ");
		String stato = sc.nextLine();
		
		//Converto la stringa in StatoSpesa
		StatoSpesa statoSpesa = stringToEnum(stato);
		
		for (Entity entity : service.visualizza()) {
			
			System.out.println("_______________________________________________");
			
			if (entity.getNota_spesa().getStato().equals(statoSpesa));
			
			NotaSpesa ns = entity.getNota_spesa(); 
			//Intestazione 
			System.out.println(String.format(
					"%5s|%29s|%6s|%7.2f|%15s|%5s|",
					"ID", "CODICE", "MESE", "IMPORTO", "STATO", "ID DIPENDENTE"
					));
			
			System.out.println(String.format(
					"%5s|%29s|%6s|%7.2f|%15s|%5s|",
					ns.getId(), ns.getCodice(), ns.getMese_rif(),
					ns.getImporto_totale(), ns.getStato(), ns.getId_dipendente()
					));
			
			for (VoceSpesa vs : entity.getVoci_spesa()) {
				
				System.out.println("_______________________________________________");
				
				System.out.println(String.format(
						"%5s|%30s|%7.2f|%5S|%5s",
						"ID", "COMMENTO", "IMPORTO", "ID NOTA SPESA", "ID CATEGORIA"
						));
				
				System.out.println(String.format(
						"%5s|%30s|%7.2f|%5S|%5s",
						vs.getId(), vs.getCommento(), vs.getImporto(), vs.getId_nota_spesa(), vs.getId_categoria()
						));
			}
			
		}
		
	}
	
	public static StatoSpesa stringToEnum(String str) {
		
		StatoSpesa stato = null;
		
		switch (str) {
		case "registrata":
			stato = StatoSpesa.REGISTRATA;
		case "in validazione":
			stato = StatoSpesa.REGISTRATA;
		case "validata":
			stato = StatoSpesa.REGISTRATA;
		case "rifiutata":
			stato = StatoSpesa.REGISTRATA;
		}
		
		return stato;
	}

}