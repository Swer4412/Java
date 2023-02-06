package it.jac.corsojava;

import java.io.IOException;

import it.jac.corsojava.bean.Persona;

public class Main1Methods {

	public static void main(String[] args) {
		
		accessModifiers();
		
		returnType();
		
		methodName();
		
		parameterList();
		
		optionalExceptionList();
		
		varArgs();
		
	}

	private static void accessModifiers() {
		
//		esistono 4 tipologie di modificatori
//		private
//		protected
//		public
//		default
		
		Persona p = new Persona();
//		il metodo pubblico è visibile da tutte le altre classi
		p.setNome("Marco");
		
//		non è visibile perchè non è nello stesso package
//		p.stampaNomeUppercase();
		
//		non è visibile perchè non è nello stesso package
//		p.stampaNomeLowercase();

//		non è visibile da nessuno perchè è privato
//		p.stampaNome();

	}

	private static void returnType() {
		
//		richiamo un metodo void
		stampaMillisecondi();
		
//		richiamo un metodo che restituisce una String
		String s = metodoStringa();
		System.out.println(s);
	}

	private static void stampaMillisecondi() {
		System.out.println(System.currentTimeMillis());
	}

	private static String metodoStringa() {
		return "x";
	}

	private static void methodName() {
		
//		i nomi dei metodi seguono le stesse regole dei nomi delle variabili
//		sono consentite quindi le lettere, i caratteri _ e $
		
//		è buona norma usare la prima lettera del nome del metodo in minuscono
	}

	private static void parameterList() {
		
//		richiamo il metodo con 1 parametro
		methodParam1(1);
		
//		richiamo il metodo con 2 parametri rispettando l'elenco e le tipologie di variabili
		methodParam2("", 2);
		
//		questo non compila
//		methodParam2("");

//		questo non compila
//		methodParam2(2, "");

	}
	
	private static void methodParam1(int i) {
		System.out.println("corpo del metodo methodParam1");
	}
	
	private static void methodParam2(String s, int i) {
		System.out.println("corpo del metodo methodParam2");		
	}

	private static void optionalExceptionList() {
		
//		richiamo un metodo che può generare un'eccezione
		try {
			
			System.out.println("A");
			methodException1();
			
			System.out.println("B");
		} catch (Exception e) {
//			gestire la situazione di errore
			
			System.out.println("C");
		}
	}
	
	private static void methodException1() throws IOException, IllegalArgumentException {
		
	}

	private static void varArgs() {
		
//		richiamo corretto
		methodVargs("", 10,20,55);
		
//		richiamo corretto
		methodVargs("", 10);

//		richiamo corretto
		int[] numeri = new int[3];
		methodVargs("", numeri);

	}
	
	private static void methodVargs(String s, int...numeri) {
		
//		ricordarsi che il parametro "vargs" può essere inserito solo come ultimo elemento
		
		System.out.println(s);
		
//		la variabile "numeri" viene vista come un array
//		posso quindi stampare la sua lunghezza o ciclarlo per stampare tutti i suoi valori
		System.out.println(numeri.length);
		for (int n : numeri) {
			
			System.out.println(n);
		}
	}
}