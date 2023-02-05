package it.jac.corsojava;

import java.util.Random;

public class Main2JavaStatements {

	public static void main(String[] args) {
		
		ifStatement();
		
		switchStatement();
		
		forStatement();
		
		whileStatement();
		
		doWhileStatement();
	}

	private static void ifStatement() {
	
		double d1 = 99.99;
		
		if (d1 >= 99 && d1 < 100) {
			
			System.out.println("valore compreso tra 99 e 100");
			
		} else if (d1 < 99 ){
			
			System.out.println("valore minore di 99");
			
		} else {
			
			System.out.println("valore maggiore o uguale a 100");
		}
		
		System.out.println((d1 > 100) ? "valore maggiore di 100" : "valore minore di 100");
		
	}
	
// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//	Scrivere un programma che richiede in input all'utente un numero e stampa se � pari o dispari
//  Scrivere un programma che richiede in input 5 numeri e stampa il numero maggiore	
// =====================================================	

	private static void switchStatement() {

		char c1 = 'a';

//		lo switch pu� funzionare con le variabili assegnabili alla tipologia int + String + enum
//		int, short, byte, char, String (dalla versione 7), enum
		switch(c1) {
		case 'a':
		case 'b': {
			System.out.println("valore a o b");
			break;
		}
		case 'c':
			System.out.println("valore c");
			break;
		default:
			System.out.println("altre casistiche");
		}
		
		int value = 2;
		final int case1 = 1;
		int case2 = 2;
		int case3 = 3;
		switch (value) {
		case case1:
			System.out.println("valore1");
			break;
		}
	}
	
// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//	Scrivere un programma che richiede in input all'utente un numero compreso tra 1 e 5
//	e nei casi 2,3,5 stampa il numero moltiplicato per 2 negli altri casi stampa il numero moltiplicato per 3
//	controllare che il numero inserito sia compreso tra 1 e 5
// =====================================================		

	private static void forStatement() {
		
		int numeroCicli = 10;
		for(int i = 0;i < numeroCicli;i++) {
			
			System.out.println("ciclo num: " + i);
		}
		
		for(int i = 0;i < numeroCicli; i++) {
			
			if (i % 2 == 0) {
				continue; // continue conclude la seguente iterazione riporta a for
			}
			if (i > 8) {
				break;
			}
			System.out.println("ciclo num: " + i);
		}
		
		//Definizione di un array
		int[] numeri = new int[6]; //tipo array[] nome = new int[lunghezza array]
		
		numeri[2] = 16; //assegno alla terza posizione dell array un valore
		System.out.println(numeri[0]);
		System.out.println(numeri[2]);
		
		for(int i = 0;i < numeri.length; i++) {
			
			System.out.println(numeri[i]);
		}
	}

// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//	Scrivere un programma che richiede in input all'utente una stringa (sequenza di caratteri) e la stampa in verticale
// =====================================================		

	private static void whileStatement() {
		
		
		int conteggio = 0;
		
		while(conteggio < 10) {
		
			int casuale = new Random().nextInt(100);
			while(casuale % 2 > 0) {
				
				System.out.println("numero generato non valido " + casuale);
				casuale = new Random().nextInt(100);
			}
			System.out.println("numero generato valido " + casuale);
			conteggio = conteggio + 1;
			
		}
	}

// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//	Scrivere un programma che continua a richiede in input all'utente un numero finch� non digita 5
//	controllare che l'utente abbia digitato un numero compreso tra 1 e 5
// =====================================================		

	private static void doWhileStatement() {

		int casuale;
		do {

			casuale = new Random().nextInt(100);
			if (casuale % 2 > 0) {
				System.out.println("numero generato non valido " + casuale);
			}
			
		} while(casuale % 2 > 0);
		
		System.out.println("numero generato valido " + casuale);
		
		do {
			
			casuale = new Random().nextInt(100);
			if (casuale % 2 > 0) {
				System.out.println("numero generato non valido " + casuale);
			} else {
				break;
			}
			
		} while(true);
		
		System.out.println("numero generato valido " + casuale);
	}
	
// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//	Scrivere un programma che stampa la tavola pitagorica
//	
//	01 02 03 04 05 06 07 08 09 10
//	02 04 06 08 10 12 14 16 18 20
//	03 06 09 12 15 18 21 24 27 30	
// =====================================================		
	
}
