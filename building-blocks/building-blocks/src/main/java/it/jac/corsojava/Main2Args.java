package it.jac.corsojava;

public class Main2Args {

	public static void main(String[] args) { //Nei file di java deve sempre esserci una funzione main

//		 per lanciare la classe dall'esterno posizionarsi con il prompt dei comandi
//		 nella cartella "../target/classes" del progetto e avviare il comando
//		 java it.jac.corsojava.Main2Args		
			
		System.out.println("numero di parametri " + args.length);

		System.out.println("quarto parametro: " + args[3]);
		
		System.out.println("lunghezza quarto parametro: " + args[3].length());
		
//		 cosa succede se lancio la classe cosi'?
//		 java it.jac.corsojava.Main2Args "e' il primo parametro" secondo		
		String s1 = "2";
		int n1 = Integer.parseInt(s1);
	}
	
// ====================================================
//	ESERCITAZIONE INSIEME
//	Scrivere una programma che riceve in input due numeri e stampa in output la loro somma
// =====================================================	
}
