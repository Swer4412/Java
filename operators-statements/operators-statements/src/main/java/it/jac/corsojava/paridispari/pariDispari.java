package it.jac.corsojava.paridispari;

import java.util.Scanner;

public class pariDispari {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inserisci un numero: ");
		
		int num = Integer.parseInt(sc.nextLine());
		
		if (num % 2 > 0) {
			System.out.println(num + " è un numero dispari");
		} else {
			System.out.println(num + " è un numero pari");
		}
	}
}
