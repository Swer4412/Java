package it.jac.corsojava;

public class Main4Overloading {

	public static void main(String[] args) {
		
		int n1 = 10;
		int n2 = 15;
		
		short n3 = 45;
		short n4 = 54;
		
		double n5 = 199.99;
		double n6 = 0.98;
		
//		somma((Integer)n1, (Integer)n2);
		
		somma(n1, n2);
		
		somma(n1, n2, n3);
		
		somma(n3, n4);
		
//		somma(n5, n6);
		
		somma(new int[] {n1, n2});
		
	}

	private static void somma(Integer numero1, Integer numero2) {
		
		System.out.println("Integer " + (numero1 + numero2));
	}

//	se presente la firma con le variabili di tipo primitivo, questa viene preferita rispetto all'uso del wrapper Integer
//	perchè fa meno lavoro
//	private static void somma(int numero1, int numero2) {
//		
//		System.out.println("int " + (numero1 + numero2));
//	}

//	NON COMPILA perchè il nome e i parametri sono gli stessi presenti in un altro metodo
//	il tipo di ritorno non viene preso in considerazione
//	private static int somma(int numero1, int numero2) {
//		
//		System.out.println("int " + (numero1 + numero2));
//		
//		return numero1 + numero2;
//	}

	private static void somma(int numero1, int numero2, int numero3) {
		
		System.out.println("int " + (numero1 + numero2 + numero3));
	}

	private static void somma(short numero1, short numero2) {
		
		System.out.println("short " + (numero1 + numero2));
	}
	
//	private static void somma(double numero1, double numero2) {
//		
//		System.out.println("double " + (numero1 + numero2));
//	}
	
	private static void somma(int[] listaNumeri) {
		
	}

//	private static void somma(int... listaNumeri) {
//		
//	}

}
