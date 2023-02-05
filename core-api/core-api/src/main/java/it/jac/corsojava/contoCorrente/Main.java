package it.jac.corsojava.contoCorrente;

import java.util.Scanner;

public class Main {
	
	
	public static void main (String[] args) {
		//Dichiaro scanner e contoCorrente
		Scanner sc = new Scanner(System.in);
		CCInterfaccia cc = new ContoCorrente();
		//Variabili per input utente
		int num;
		String str;		
		
		//Inizio interfaccia
		System.out.println("Benventuo nella tua banca");
		while (true) {
				System.out.println("1 Deposita\n"
						+ "2 Preleva\n"
						+ "3 Elenco movimenti\n"
						+ "4 Visualizza saldo\n"
						+ "5 Ricarica telefono\n"
						+ "6 ESCI");
				
				String scelta = sc.nextLine();
				if (scelta=="6") {
					break;
				}
				
				switch (scelta) {
				case "1":
					System.out.println("Inserisci somma da depositare: ");
					str = sc.nextLine();
					num = Integer.parseInt(str);
					cc.deposita(num);
					break;
				case "2":
					System.out.println("Inserisci somma da prelevare: ");
					str = sc.nextLine();
					num = Integer.parseInt(str);
					System.out.println(cc.preleva(num));
					break;
				case "3":
					System.out.println("Inserisci un periodo: ");
					str = sc.nextLine();
					System.out.println(cc.getMovimenti(str));
					break;
				case "4":
					System.out.println("Il tuo saldo é: " + cc.getSaldo());
					break;
				case "5":
					System.out.println("Inserisci quanto vuoi caricare: ");
					str = sc.nextLine();
					num = Integer.parseInt(str);
					if (cc.ricaricaTel(num)) {
						System.out.println("RIcarica effettuata");
					}
					break;
		
			}
	 	}
	}
}
//====================================================
//	ESERCITAZIONE IN AUTONOMIA/GRUPPO
//	Definire le classi necessarie a scrivere un programma in grado di simulare una gestione semplificata di un conto corrente.
//	In particolare il conto corrente ammette le seguenti operazioni:
//
//		deposito - il programma permette la registrazione di un movimento che incrementa il saldo del conto corrente
//		prelievo - il programma permette la registrazione di un movimento che decrementa il saldo del conto corrente
//		elenco movimenti - il programma permette di visualizzare tutti i movimenti registrati in un lasso di tempo (data inizio e data fine)
//		saldo - il programma permette di visualizzare il saldo attuale contenuto nel conto corrente
//		ricarica telefonica - il programma permette di eseguire una ricarica telefonica	ricarica(numTelefono)
//=====================================================	
	