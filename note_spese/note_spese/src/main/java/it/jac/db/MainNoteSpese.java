package it.jac.db;

import java.time.LocalDateTime; 
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import it.jac.db.entity.Categoria;
import it.jac.db.entity.Dipendente;
import it.jac.db.entity.NotaSpesa;
import it.jac.db.entity.VoceSpesa;
import it.jac.db.exception.EntityNotFoundException;
import it.jac.db.exception.ValidateNotaSpesaException;
import it.jac.db.service.AuthService;
import it.jac.db.service.NotaSpesaService;

public class MainNoteSpese {

	public static void main(String[] args) {
		
		System.out.println("*** APPLICAZIONE NOTE SPESE ***");
		
		Scanner scanner = new Scanner(System.in);

		String matricola = richiediMatricola(scanner);
		
		boolean termina = false;
		do {
			
			if (matricola != null) {
				System.out.println("1) Inserisci nota spesa");
				System.out.println("2) Elimina nota spesa");
			}
			if (matricola == null) {
				
				System.out.println("3) Elenco note spesa DA VALIDARE");
				System.out.println("4) Prendi in carico nota spesa");
				System.out.println("5) Valida nota spesa");
			}
			System.out.println("6) Elenco note spese per stato");
			System.out.println("99) Esci");
			
			System.out.print("Scegli funzione: ");
			String scelta = scanner.nextLine();
			switch (scelta) {
			
			case "1":
				creaNota(scanner, matricola);
				break;
			case "2":
				eliminaNota(scanner, matricola);
				break;
			case "3":
				if (matricola == null) {
					elencoDaValidare(scanner);
					break;
				}
			case "4":
				if (matricola == null) {
					prendiInCaricoNota(scanner);
					break;
				}
			case "5":
				if (matricola == null) {
					validaNota(scanner);
					break;
				}
			case "6":
				elencoByStato(scanner, matricola);
				break;
			case "99":
				termina = true;
				break;
			default:
				System.out.println("Scelta non valida");
			}
			
			
		} while (!termina);
	}

	private static void creaNota(Scanner scanner, String matricola) {
		
		NotaSpesa notaSpesa = new NotaSpesa();
		
//		mese riferimento
		System.out.print("Inserisci il mese rif (MMMMDD): ");
		notaSpesa.setMeseRif(scanner.nextLine());

//		scelta dipendente
		Dipendente dipendente = null;
		try { //I try catch servono per gestire le throws
			//getIstance() ritorna la istanza statica
			//L'istanza statica è stata creata appena partito il programma
			dipendente = NotaSpesaService.getInstance().findDipendenteByMatricola(matricola);
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
		notaSpesa.setDipendente(dipendente);
		
		boolean termina = false;
		do {
			VoceSpesa dettaglio = new VoceSpesa();
			
//			richiesta inserimento voci spesa
			System.out.println("Voce Spesa - Inserisci commento: ");
			dettaglio.setCommento(scanner.nextLine());
			
			System.out.println("Voce Spesa - Inserisci categoria: ");
			Categoria categoria = null;
			
			try {
				categoria = NotaSpesaService.getInstance().findCategoriaByCod(scanner.nextLine());
			} catch (EntityNotFoundException e) {
				System.out.println(e.getMessage());
				continue;
			}
			dettaglio.setCategoria(categoria);
			
			System.out.println("Voce Spesa - Inserisci importo: ");
			dettaglio.setImporto(Double.parseDouble(scanner.nextLine()));
						
			System.out.print("Scegli se proseguire [Y, N]: ");
			termina = "n".equalsIgnoreCase(scanner.nextLine()); 
			
			notaSpesa.getVociSpesa().add(dettaglio);
			
		} while (!termina);
		
		try { 
			NotaSpesaService.getInstance().create(notaSpesa);
		} catch (EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void eliminaNota(Scanner scanner, String matricola) {
		
		System.out.print("Inserisci ID: ");
		String id = scanner.nextLine();
		
		try {
			
			NotaSpesaService.getInstance().delete(Integer.parseInt(id), matricola);
			System.out.println("Nota spesa eliminata!");
			
		} catch (NumberFormatException e) {
			
			System.out.println("ID non valido");
			
		} catch(EntityNotFoundException | ValidateNotaSpesaException e) {
			
			System.out.println(e.getMessage());
		}
	}

	private static void elencoDaValidare(Scanner scanner) {
		
		List<NotaSpesa> list = NotaSpesaService.getInstance().findNoteSpesaByStato(NotaSpesaService.STATO_REGISTRATA);
		stampaElenco(list);
	}

	private static void prendiInCaricoNota(Scanner scanner) {
		
		System.out.print("Inserisci ID: ");
		String idStr = scanner.nextLine();
		int id = 0;
		
		try {
			
			id = Integer.parseInt(idStr);
			NotaSpesaService.getInstance().prendiInCaricoNotaSpesa(id);
						
		} catch (NumberFormatException e) {
			
			System.out.println("ID non valido");
			
		} catch(EntityNotFoundException | ValidateNotaSpesaException e) {
			
			System.out.println(e.getMessage());
		}
	}

	private static void validaNota(Scanner scanner) {
		
		System.out.print("Inserisci ID: ");
		String idStr = scanner.nextLine();
		int id = 0;
		
		try {
			
			id = Integer.parseInt(idStr);
			NotaSpesa n = NotaSpesaService.getInstance().findNotaSpesaById(id);
			
			System.out.println("--------------------------------------------------------");
			System.out.println(String.format("%5s|%30s|%6s|%8s|%15s|%30s", "ID", "COD", "MESE", "IMPORTO", "STATO", "DIP."));
			System.out.println("--------------------------------------------------------");
			
			System.out.println(String.format("%5s|%30s|%6s|%8.2f|%15s|%30s", 
				n.getId(), n.getCod(), n.getMeseRif(), n.getImportoTotale(), n.getStato(),
				n.getDipendente().getCognome()));

			System.out.println("--------------------------------------------------------");
			System.out.println(String.format("%25s|%8s|%30s", "CATEGORIA", "IMPORTO", "COMMENTO"));

			List<VoceSpesa> dettaglioList = n.getVociSpesa();
			for (VoceSpesa v : dettaglioList) {
				
				System.out.println(String.format("%25s|%8.2f|%30s", v.getCategoria().getDescrizione(), v.getImporto(), v.getCommento()));
			}
			
			System.out.println("A - APPROVA");
			System.out.println("R - RIFIUTA");
			System.out.println("X - ANNULLA");
			System.out.print("Scegli: ");
			String scelta = scanner.nextLine();
			if ("A".equalsIgnoreCase(scelta)) {
				
				NotaSpesaService.getInstance().approvaNotaSpesa(id, n);
				
			} else if ("R".equalsIgnoreCase(scelta)) {
				
				NotaSpesaService.getInstance().rifiutaNotaSpesa(id, n);
			}
			
		} catch (NumberFormatException e) {
			
			System.out.println("ID non valido");
			
		} catch(EntityNotFoundException | ValidateNotaSpesaException e) {
			
			System.out.println(e.getMessage());
		}
	}

	private static void elencoByStato(Scanner scanner, String matricola) {
		
		Map<String, String> mapStato = new LinkedHashMap<>();
		mapStato.put("A", NotaSpesaService.STATO_REGISTRATA);
		mapStato.put("B", NotaSpesaService.STATO_IN_VALIDAZIONE);
		mapStato.put("C", NotaSpesaService.STATO_RIFIUTATA);
		mapStato.put("D", NotaSpesaService.STATO_VALIDA);

		Set<Entry<String, String>> entrySet = mapStato.entrySet();
		for(Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		
		System.out.print("Seleziona lettera: ");
		String lettera = scanner.nextLine();
		String stato = mapStato.get(lettera);
		
		List<NotaSpesa> list = NotaSpesaService.getInstance().findNoteSpesaByStato(stato);
//		se la matricola e' valorizzata filtro solo le sue
		if (matricola != null) {
			list = list.stream().filter(n -> n.getDipendente().getMatricola().equals(matricola)).collect(Collectors.toList());
		}
		stampaElenco(list);
	}

	private static String richiediMatricola(Scanner scanner) {
		
		String matricola = null;
		
		boolean termina = false;
		
		do {
			System.out.println("--> LOGIN UTENTE");
			System.out.println("D) Dipendente");
			System.out.println("A) Amministratore");
			System.out.print("Scegli come entrare: ");
			String ruolo = scanner.nextLine();
			if ("D".equalsIgnoreCase(ruolo)) {
				
				System.out.print("Inserisci matricola: ");
				matricola = scanner.nextLine();
				try {
					
					Dipendente dipendente = AuthService.getInstance().loginDipendente(matricola);
					System.out.println("Benvenuto " + dipendente.getCognome() + " " + dipendente.getNome());
					termina = true;
					
				} catch (EntityNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
				
			} else if ("A".equalsIgnoreCase(ruolo)) {
				
				System.out.print("Inserisci password: ");
				String password = scanner.nextLine();
				if (AuthService.getInstance().loginAdmin(password)) {
					System.out.println("Benvenuto ADMIN");
					termina = true;
				}
				
			}
		} while (!termina);
		
		return matricola;
	}
	
	private static void stampaElenco(List<NotaSpesa> list) {
		
		System.out.println("--------------------------------------------------------");
		System.out.println(String.format("%5s|%30s|%6s|%8s|%15s|%30s", "ID", "COD", "MESE", "IMPORTO", "STATO", "DIP."));
		System.out.println("--------------------------------------------------------");
		
		for(NotaSpesa n : list) {
			System.out.println(String.format("%5s|%30s|%6s|%8.2f|%15s|%30s", 
				n.getId(), n.getCod(), n.getMeseRif(), n.getImportoTotale(), n.getStato(),
				n.getDipendente().getCognome()));			
		}
		
	}
}
