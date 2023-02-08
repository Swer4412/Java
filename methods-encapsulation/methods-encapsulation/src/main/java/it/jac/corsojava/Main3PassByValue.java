package it.jac.corsojava;

import java.util.ArrayList;

import it.jac.corsojava.bean.Persona;

public class Main3PassByValue {

	public static void main(String[] args) {
	
		int num = 10;
		
		changeValue(num); //Quando si passa un tipo primitivo, viene passato il valore
		
		System.out.println("num dopo il richiamo della funzione: " + num);
		
		String str = "eclipse";
		
		changeValue(str);
		
		System.out.println("str dopo il richiamo della funzione: " + str);

		ArrayList<String> list = new ArrayList<>();
		list.add("eclipse");
		
		changeValue(list); //Quando si passa un oggetto , viene passato l'indirizzo di memoria
		
		System.out.println("dimensione dopo il richiamo della funzione: " + list.size());
	
		Persona p = new Persona();
		
		changeValue(p);
		
		System.out.println(p.getNome());
		
		
	}
	private static void changeValue(Persona p) {
		p.setNome("Andrea");
	}

	private static void changeValue(ArrayList<String> list) { // Dato che è un oggetto viene passato l'indirizzo di memoria, 

		list.add("visual studio code");
		System.out.println("dimensione all'interno del metodo: " + list.size());
	}

	private static void changeValue(String str) { // Viene passato il valore "eclipse" in una variabile nuova

		str = str.toUpperCase();
		
		System.out.println("str all'interno del metodo: " + str);
	}

	private static void changeValue(int num) { //Il num qui non è lo stesso num, viene copiato il valore

//		assegno a num un nuovo valore
		num = 5; //Sto cambianodo il valore della copia num
		
		System.out.println("num all'interno del metodo: " + num); // Ritorno la copia di num
	}

// ====================================================
//	ESERCITAZIONE IN AUTONOMIA/GRUPPO
//	creare un'applicazione in grado di gestire un archivio di fatture
//	ogni fattura � composta da
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
