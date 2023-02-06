package it.jac.corsojava;

import it.jac.corsojava.bean.Badge;

public class Main5Constructors {

	public static void main(String[] args) {
		
		Badge badge1 = new Badge();
		badge1.setCodice("jac_ict21_01");
		badge1.setNome("Armando");
		badge1.setCognome("Esposito");
		
//		come faccio a stampare in output il contenuto della classe Badge formattata?
//		proviamo ad implementare il metodo toString() nella classe Badge
		System.out.println(badge1);
		
		Badge badge2 = new Badge("jac_ict21_02");
		badge2.setNome("Daniele");
		badge2.setCognome("Montano");
		System.out.println(badge2);

		Badge badge3 = new Badge("jac_ict21_03", "Maria", "Valente");
		System.out.println(badge3);
	}

// ====================================================
//	ESERCITAZIONE IN AUTONOMIA
//	Definire una classe GestoreMessaggi che memorizza al suo interno una lista di messaggi
//	Deve essere possibile istanziare l'oggetto usando il costruttore di default oppure registrando un messaggio 
//	oppure registrando un elenco di messaggi
//	
//	La classe deve esporre le operazioni per
//	- aggiungere un nuovo messaggio
//	- pulire l'elenco dei messaggi
//	- restituire il conteggio dei messaggi
// =====================================================	

}
