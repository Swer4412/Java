package it.jac.corsojava.dizionario;

import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static dizionario diz = new dizionario();
	
	public static final String VERDE = "\u001B[32m";
	public static final String AZZURRO= "\u001B[36m";
	public static final String RESET = "\u001B[0m";
	
	public static void main(String[] args) {
		String str;
		do {
			System.out.println(RESET + "1 = Inserimento 2 = Traduzione");
			str = sc.nextLine();
			if (str.equals("1")) {
				inserisci();
			} else if (str.equals("2")) {
				traduci();
			}
		} while (str != "");
		System.out.println("Fine programma");
	}
	
	public static void inserisci() {
		String str;
		do {
			//Scelta della lingua
			System.out.println(VERDE + "Inserimento\nScegli una lingua ("+ diz.getLingue() +"): ");
			str = sc.nextLine();
			//Inserimento delle parole
			if (diz.checkLingua(str)) {
				System.out.println("Inserisci parola in " + diz.getLingua() + ":");
				str = sc.nextLine();
				diz.setParola(str);
				System.out.println("Inserisci traduzione in italiano: ");
				str = sc.nextLine();
				diz.setParola(str);
			} else if (str == "") {
				break;
			} else {
				System.out.println(str + " non registrata ");
			}
		} while (str != "");
			
	}
	public static void traduci() {
		String str;
		do {
			//Scelta della lingua
			System.out.println(AZZURRO + "Traduzione\nScegli una lingua ("+ diz.getLingue() +"): ");
			str = sc.nextLine();
			//Inserimento delle parole
			if (diz.checkLingua(str)) {
				System.out.println("Inserici parola in " + diz.getLingua());
				str = sc.nextLine();
				System.out.println("Traduzione: " + diz.getParola(str));
			} else if (str == "") {
				break;
			} else {
				System.out.println(str + " non registrata ");
			}
		} while (str != "");
	}
}








