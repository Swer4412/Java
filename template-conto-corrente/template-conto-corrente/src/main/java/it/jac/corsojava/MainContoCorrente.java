package it.jac.corsojava;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.bean.ContoCorrente;
import it.jac.corsojava.bean.Movimento;

public class MainContoCorrente {
	//Il logger è uno strumento che prende il log del programma in esecuzione
	private static Logger log = LogManager.getLogger(MainContoCorrente.class); //Indicando MainContoCorrente indico tutte le classi?
	//
	public static void main(String[] args) {
		
		log.info("Applicazione avviata"); //Tracciamento che l'applicazione si è avviata
//		log.info() è istruito (in log4j2.xml) a scrivere:
//		14:40:27.571 [main] INFO  it.jac.corsojava.MainContoCorrente - Applicazione avviata
//		Invece di System.out.println() da ora utilizza i log dato che con loro puoi facilmente scrivere i log su un file
		
		Scanner scanner = new Scanner(System.in);
		
		ContoCorrente cc = new ContoCorrente();
		boolean esc = false;
		do {
			
			System.out.println("***MENU APPLICAZIONE***");
			System.out.println("1) Deposito");
			System.out.println("2) Prelievo");
			System.out.println("3) Ricarica telefonica");
			System.out.println("4) Saldo");
			System.out.println("5) Lista Movimenti");
			
			System.out.println("******");
			
			System.out.print("Scegli operazione: ");
			String scelta = scanner.nextLine();
			
			switch(scelta) {
			
			case "1": {
				
				deposito(scanner, cc);
				break;
			}
			case "2": {
				
				prelievo(scanner, cc);
				break;
			}
			case "3": {
				
				ricaricaTel(scanner, cc);
				break;
			}
			case "4": {
				
				saldo(scanner, cc);
				break;
			}
			case "5": {
				
				listaMovimenti(scanner, cc);
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

	private static void deposito(Scanner scanner, ContoCorrente cc) {

		log.debug("Operazione deposito");
		// TODO
		// richiedere importo all'utente
		System.out.println("Inserisci importo del deposito: ");
		String strValore = scanner.nextLine();
		log.debug("Deposito {}", strValore);
		
		// richiamare funzione del ContoCorrente per memorizzare l'operazione
		cc.deposito(Double.parseDouble(strValore));
	}

	private static void prelievo(Scanner scanner, ContoCorrente cc) {
		
		log.debug("Operazione prelievo");
	}

	private static void ricaricaTel(Scanner scanner, ContoCorrente cc) {
		
		log.debug("Operazione ricaricaTel");
	}

	private static void saldo(Scanner scanner, ContoCorrente cc) {
		
		log.debug("Operazione saldo");
	}

	private static void listaMovimenti(Scanner scanner, ContoCorrente cc) {
		
		log.debug("Operazione lista movimenti");
		
		
		ArrayList<Movimento> list = cc.getElencoMovimenti();
		for (Movimento movimento : list) {
			System.out.println(movimento.toString());
		}
	}
}
