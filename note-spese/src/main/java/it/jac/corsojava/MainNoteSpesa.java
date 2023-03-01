package it.jac.corsojava;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ServiceNoteSpesa;
import it.jac.corsojava.dao.NoteSpesaDao;
import it.jac.corsojava.entity.VoceSpesa;
import it.jac.corsojava.entity.StatoSpesa;

public class MainNoteSpesa {

	private static Logger log = LogManager.getLogger(MainNoteSpesa.class);

	public static void main(String[] args) {

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
		
		
	}

	private static void inserisciNotaSpese(Scanner sc, ServiceNoteSpesa service) {
		
		
		if (service.inserisci(entity)) {
			System.out.println("Inserimento avvenuto con successo");
		} else {
			System.out.println("Problema nell'inserimento");
		}
	}

	private static void eliminaNotaSpese(Scanner sc, ServiceNoteSpesa service) {
		
		System.out.println("Inserisci l'id della nota spese da eliminare: ");
		int id =Integer.parseInt(sc.nextLine());
		
		if (service.elimina(id)) {
			System.out.println("Eliminazione avvenuta con successo");
		} else {
			System.out.println("Problema nell'eliminazione");
		}
		
	}

	private static void visualizzaNoteValidare(Scanner sc, ServiceNoteSpesa service) {
		
		System.out.println(service.visualizza("registrata"));
		
	}

	private static void validaNota(Scanner sc, ServiceNoteSpesa service) {
		// TODO Auto-generated method stub
		
	}

	private static void visualizzaNoteDiStato(Scanner sc, ServiceNoteSpesa service) {
		
		System.out.println("Inserisci lo stato che vuoi visualizzare: ");
		String stato = sc.nextLine();
		
		
		System.out.println(service.visualizza(stato);
		
	}

}