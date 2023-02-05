package it.jac.corsojava.cicli;

import java.util.Scanner;

//Scrivere un programma che richiede in input all'utente un numero compreso tra 1 e 5
//e nei casi 2,3,5 stampa il numero moltiplicato per 2 negli altri casi stampa il numero moltiplicato per 3
//controllare che il numero inserito sia compreso tra 1 e 5

public class switchCase {
	
	public static void main(String[] args) {
		System.out.println("Inserisci un numero tra 1 e 5: ");
		//Dichiaro scanner
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		
		//Controllo range input
		if (num>0 & num<6) {
			switch (num) {
			case 2:
				System.out.println(num*2);
				break;
			case 3:
				System.out.println(num*2);
				break;
			case 5:
				System.out.println(num*2);
				break;
			}
		} else {
			System.out.println("Inserisci un numero tra 1 e 5");
		}
		
		sc.close();
		
	}
}
