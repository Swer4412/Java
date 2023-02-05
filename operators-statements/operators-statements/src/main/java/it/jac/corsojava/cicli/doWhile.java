package it.jac.corsojava.cicli;

public class doWhile {
	
	public static void main(String[] args) {
		//Definisco array
		int[] numeri= new int[10];
		int[] tabella = new int[100];
		
		//Riempio array
		for (int i = 0; i<numeri.length; i++) {
			numeri[i] = i+1;
		}
		
		//Definisco contatori
		int i = 0;
		int j = 0;
		int k = 0;
		
		do {
			j = 0;
			do {
				System.out.print(String.format("%5s",(numeri[j]*numeri[i] + (j == 9 ? "\n": ""))));
				j++;
			} while(j<numeri.length);
			i++;
		} while(i<numeri.length);
	}
}
