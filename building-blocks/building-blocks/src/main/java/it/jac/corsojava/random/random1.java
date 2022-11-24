package it.jac.corsojava.random;

import java.util.Random;
// alt + shift + l serve per estrarre la variabile
// alt + shift + r serve per rinominare le variabili con lo stesso nome

public class random1 { //Ricorda che le classi si devono chiamare come il file

	public static void main(String[] args) {
		 getCasuale(); //Lascio dentro stampa in modo che se eseguo da questo file funziona uguale
	}
	
	// Convenzione: get usato per le funzioni che ritornano qualcosa
	public static int getCasuale() { //int dice che la funzione ritorna un itnero
		Random random = new Random();
		int num = random.nextInt(100); // new crea in memoria un nuova area con l'oggetto Random e le sue proprietà
		Boolean bool = random.nextBoolean(); // Creando una istanza di random permette di usarlo anche per altre sue

		return(num);
	}
	

}
