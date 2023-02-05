package it.jac.corsojava.cicli;


import java.util.Scanner;

public class cicloWhile {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		
		while (num != 5) {
			System.out.println("Inserisci un numero: ");
			num = Integer.parseInt(sc.nextLine());
			
			if (num<0 || num>5) {
				System.out.println("Inserisci un numero tra 1 e 5");
			}
		}
		System.out.println("Bravo hai inserito " + num);
	}
}
