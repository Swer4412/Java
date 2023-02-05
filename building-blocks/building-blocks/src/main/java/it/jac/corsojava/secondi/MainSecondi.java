package it.jac.corsojava.secondi;

import java.util.Scanner;

public class MainSecondi {
	
	static int SECONDI_GIORNO= 86400;
	private int ore;
	private int minuti;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inserisci orario");
		
		String orario = sc.nextLine();
		
		int ore = Integer.parseInt(orario.substring(0,2)); //stringa.substring(indice di inizio, indice di fine)
		int minuti = Integer.parseInt(orario.substring(3)); // esso ritorna i caratteri che si trovano
		
		MainSecondi obj1 = new MainSecondi();
		obj1.ore = ore;
		obj1.minuti = minuti;
		
		int risultato = obj1.getSecondi();
		
		System.out.println(SECONDI_GIORNO - risultato);
		
	}
	public int getSecondi() {
		int totMinuti = this.ore*60 + this.minuti;
		int totSecondi = totMinuti*60;
		
		return totSecondi;
	}
}
