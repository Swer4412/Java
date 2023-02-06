package it.jac.corsojava;

import java.util.Arrays;
import java.util.List;

import it.jac.corsojava.bean.Aula;

// import statico del metodo...poco usato
import static java.util.Arrays.asList;

public class Main2StaticVsInstance {

	public static void main(String[] args) {
		
		callFieldOrMethod();
		
		staticVariables();
		
		staticImports();
		
	}

	private static void callFieldOrMethod() {
				
		Aula aula305 = new Aula();
		aula305.configura("305", 30);
		aula305.occupaPosti(25);
//		� possibile fare riferimento alla variabile postiTotali sia con il nome della classe "Aula"
		Aula.postiTotali += 25;
//		sia con il nome della variabile "aula305", anche se confonde le idee perch� la variabile statica � relativa alla classe e non all'istanza
//		aula305.postiTotali += 25;
		
		System.out.println("sono disponibili ora " + aula305.getPostiDisponibili());
		
		Aula aula107 = new Aula();
		aula107.configura("107", 20);
		aula107.occupaPosti(12);
		Aula.postiTotali += 12;
		System.out.println("sono disponibili ora " + aula107.getPostiDisponibili());

		System.out.println("posti totali " + Aula.postiTotali);
	}

	private static void staticVariables() {
		
		System.out.println("numero massimo di posti liberi " + Aula.MAX_POSTI);
		System.out.println("numero minimo di posti liberi " + Aula.MIN_POSTI);
	}

	private static void staticImports() {
		
		List<Integer> list1 = Arrays.asList(1,2,3,4,5);
		
//		è possibile scrivere anche solo asList(1,2,3,4,5) se si esegue un import static del metodo
//		scrittura poco usata
		asList(1,2,3,4,5);
	}

}