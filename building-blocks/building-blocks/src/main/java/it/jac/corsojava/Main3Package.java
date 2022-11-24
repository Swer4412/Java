package it.jac.corsojava;

// Ctrl + Shift + o fa sistemare al IDE gli import

import it.jac.corsojava.random.random1;

public class Main3Package {

	public static void main(String[] args) {
		//Ctrl + 7 mette i commenti sulle righe selezionate
//		File file = new File("c:\tmp\file1.txt"); // non compila se non usiamo l'istruzione "import java.io.File"
//		System.out.println(file.length()); // new forma una nuova istanza(oggetto) della classe
//		
//		Date date = new Date(); // conflitto di nomi tra le classi "java.util.Date" e "java.sql.Date"
//		System.out.println(date); //Date senza parametro prende la data attuale
		
		System.out.println(random1.getCasuale());
		
	}
	
// ====================================================
// ESERCITAZIONE IN AUTONOMIA
// Creare una nuova classe in un nuovo package che stampa un numero casuale
// usando l'istruzione
// new Random().nextInt();
// =====================================================		
}
