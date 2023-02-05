package it.jac.corsojava.traduttore;

import java.util.Scanner;

public class MainTraduttore {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) { // Il try fa in modo che venga chiuso lo scanner, meglio togliere gli input della tastiera
			Traduttore traduttore = new TraduttoreList(); //Traduttore (il primo) è chiamato interfaccia
			// traduttore ha i metodi indicati nell interfaccia Traduttore e dato che TraduttoreList implementa tale interfaccia, si può creare un oggetto
			int num = 0;
			do {
			
				System.out.println("1 - Registra Inglese");
				System.out.println("2 - Registra Francese");
				System.out.println("3 - Traduci Inglese");
				System.out.println("4 - Traduci Francese");
				
				num = leggiInput(scanner);
				
	//			faccio qualcosa
				switch(num) {
				
				case 1:
					
					inserisciTraduzioneEn(scanner, traduttore);
					break;
				case 2:
					
					inserisciTraduzioneFr(scanner, traduttore);
					break;
				case 3:
					
					traduciEn(scanner, traduttore);
					break;
				case 4:
					
					traduciFr(scanner, traduttore);
					break;
				}
				
			} while(num != 5);
		}		
		System.out.println("programma termina");

	}
	
	private static void inserisciTraduzioneEn(Scanner scanner, Traduttore traduttore) {

		System.out.print("Inserisci parola inglese: ");
		String vocaboloEn = scanner.nextLine();
		System.out.print("Inserisci traduzione: ");
		String traduzione = scanner.nextLine();
		
		traduttore.registraInglese(vocaboloEn, traduzione);
	}

	private static void inserisciTraduzioneFr(Scanner scanner, Traduttore traduttore) {
		
		System.out.print("Inserisci parola francese: ");
		String vocaboloFr = scanner.nextLine();
		System.out.print("Inserisci traduzione: ");
		String traduzione = scanner.nextLine();
		
		traduttore.registraFrancese(vocaboloFr, traduzione);
	}

	private static void traduciEn(Scanner scanner, Traduttore traduttore) {
		
		System.out.print("Inserisci parola inglese da tradurre: ");
		String vocaboloEn = scanner.nextLine();
		String traduzione = traduttore.traduciInglese(vocaboloEn);
		if (traduzione == null) {
			System.out.println("La parola inserita non � presente nel dizionario");
		} else {
			System.out.println("Traduzione: " + traduzione);
		}
		
	}

	private static void traduciFr(Scanner scanner, Traduttore traduttore) {
		
		System.out.print("Inserisci parola francese da tradurre: ");
		String vocaboloFr = scanner.nextLine();
		String traduzione = traduttore.traduciFrancese(vocaboloFr);
		if (traduzione == null) {
			System.out.println("La parola inserita non � presente nel dizionario");
		} else {
			System.out.println("Traduzione: " + traduzione);
		}
	}

	private static int leggiInput(Scanner scanner) {

		int scelta;
		do {
			scelta = Integer.parseInt(scanner.nextLine());
		} while(scelta < 1 || scelta > 5);

		return scelta;
	}
	
}
