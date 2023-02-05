package it.jac.corsojava.cicli;

import java.util.Scanner;

public class cicloFor {

	public static void main(String[] args) {
		System.out.println("Inserisci una stringa : ");
		
		//Input
		Scanner sc = new Scanner(System.in);
		String stringa = sc.nextLine(); //Prendo la stringa dall input
		
		//Output
		for (int i=0; i < stringa.length(); i++) {
			System.out.println(stringa.charAt(i));
		}
	}
}
