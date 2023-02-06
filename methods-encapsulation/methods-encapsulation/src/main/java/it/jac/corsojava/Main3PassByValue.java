package it.jac.corsojava;

import java.util.ArrayList;

public class Main3PassByValue {

	public static void main(String[] args) {
	
		int num = 10;
		
		changeValue(num);
		
		System.out.println("num dopo il richiamo della funzione: " + num);
		
		String str = "eclipse";
		
		changeValue(str);
		
		System.out.println("str dopo il richiamo della funzione: " + str);

		ArrayList<String> list = new ArrayList<>();
		list.add("eclipse");
		
		changeValue(list);
		
		System.out.println("dimensione dopo il richiamo della funzione: " + list.size());
	}

	private static void changeValue(ArrayList<String> list) {

		list.add("visual studio code");
		System.out.println("dimensione all'interno del metodo: " + list.size());
	}

	private static void changeValue(String str) {

		str = str.toUpperCase();
		
		System.out.println("str all'interno del metodo: " + str);
	}

	private static void changeValue(int num) {

//		assegno a num un nuovo valore
		num = 5;
		
		System.out.println("num all'interno del metodo: " + num);
	}

// ====================================================
//	ESERCITAZIONE IN AUTONOMIA/GRUPPO
//	creare un'applicazione in grado di gestire un archivio di fatture
//	ogni fattura è composta da
//		numero fattura (deve essere un progressivo per anno di riferimento es. 01/2021, 02/2021, etc..)
//		data fattura
//		cliente di riferimento (codice, denominazione, piva, indirizzo)
//		importo fattura
//
//	l'applicazione deve mettere a disposizione le seguenti operazioni
//		registrazione di una nuova fattura
//		elenco di tutte le fatture registrate
//		calcolo del totale fatture
//		lista delle fatture di un determinato cliente
//		elenco dei clienti

}
