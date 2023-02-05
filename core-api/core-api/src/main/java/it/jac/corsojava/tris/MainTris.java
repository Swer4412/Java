package it.jac.corsojava.tris;

import java.util.Scanner;

public class MainTris {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		GiocoTris tris = new GiocoTris();
		
		tris.iniziaPartita();
		
		tris.stampaCampo(System.out);
		
		do {
			System.out.println("E' il turno del simbolo " + tris.getSimboloTurno());
			System.out.print("Inserisci la posizione della giocata: ");
			String str = scanner.nextLine();
			int posizione = Integer.parseInt(str);
			
//			interpretare il messaggio in uscita
			String message = tris.gioca(posizione);
			if (message != null) {
				System.out.println(message);
			}
			
			tris.stampaCampo(System.out);
			
		} while(tris.isPartitaInCorso());
		
		System.out.println("Ha vinto " + tris.getVincitore());
		System.out.println("Partita durata " + (tris.getEndTime() - tris.getStartTime()));
	}
}
