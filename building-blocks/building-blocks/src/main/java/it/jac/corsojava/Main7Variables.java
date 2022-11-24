// se presente deve essere posizionato come prima riga utile del file
package it.jac.corsojava;

// se presente deve essere posizionato dopo il package
import java.io.File;

// OBBLIGATORIO, dopo package e import se presenti
public class Main7Variables {
	
//	variabile statica associata alla dichiarazione di classe
	static int z;
	
//	variabile di istanza associata all'oggetto creato con l'operatore "new"
	int w;

//	variabili statiche...controlliamo i valori di default
	static boolean fl1;
	static byte b1;
	static short sh1;
	static int i1;
	static long l1;
	static float f1;
	static double d1;
	static char c1;
	static File obj1;
	
	public static void main(String[] args) {

		localVariables();
		
		instanceClassVariables();
		
		variableScopes();
		
	}

	private static void localVariables() {
		
		System.out.println("il valore di z e': " + z);
		
//		System.out.println("il valore di w e': " + w);
		
//		variabile locale 
//		il suo valore di default non esiste, deve essere inizializzata prima di usarla all'interno di un'espressione
		int x;
//		System.out.println("il valore di x e': " + x); // x deve essere inizializzato!
		
		String s;
//		System.out.println("il valore di s e': " + s); // s deve essere inizializzato!
		
		x = 10;
		System.out.println("il valore di x e': " + x); // x puo' essere usata in un'espressione perche' ha un valore
		
		int y;
		boolean flag = false;
		if (flag) {
			
			y = 2;
			
		} else {
			
			y = 3;
		}
		System.out.println("il valore di y e': " + y); 
		// il compilatore riconosce anche in presenza di strutture decisionali se la variabile viene inizializzata
	}

	private static void instanceClassVariables() {
		
		System.out.println("default boolean [" + fl1 + "]");
		System.out.println("default byte [" + b1 + "]");
		System.out.println("default short [" + sh1 + "]");
		System.out.println("default int [" + i1 + "]");
		System.out.println("default long [" + l1 + "]");
		System.out.println("default float [" + f1 + "]");
		System.out.println("default double [" + d1 + "]");
		System.out.println("default char [" + c1 + "]");
		System.out.println("default Object [" + obj1 + "]");		
	}

	private static void variableScopes() {
		
		boolean flag = true;
		if (flag) {
			int posto = 15;
			{
				int numero = posto;
			}
//			System.out.println(numero);
			
		}
//		System.out.println(posto);
	}
	
// ====================================================
//	ESERCITAZIONE IN AUTONOMIA/GRUPPO
//	Scrivere un programma che riceve in input un orario (es. 14:28) e calcola quanti secondi mancano alla mezzanotte
// =====================================================	
}
