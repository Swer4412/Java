package it.jac.corsojava.tris;

import java.util.Scanner;

public class tris {
	
	public static void main(String[] args) {
		//Introduzione
		System.out.println("Benvenuto a tetris!");
		
		//Gestione di più giochi
		Scanner sca = new Scanner(System.in);
		do {
		gioco();
		System.out.println("Se vuoi giocare ancora, scrivi: si");
		} while (sca.nextLine() != "");
		System.out.println("Alla prossima!");
	}
	
	public static void gioco() {
		//Definisco scanner
		Scanner sc = new Scanner(System.in);
		//Definizione matrice e rimepimento
		char[][] matrice = {
				{'1','2','3'},
				{'4','5','6'},
				{'7','8','9'}
		}; 
		
		char giocatore = 'X';
		boolean stop = false;
		while (!stop) {
			visualizza(matrice);
			//Input e modifica della matrice
			System.out.println("("+ giocatore + ") Inserisci posizione: ");
			int[] pos = posizione(Integer.parseInt(sc.nextLine()));
			int x = pos[0];
			int y = pos[1];
			matrice[x][y]= giocatore;
			//Controllo della vincita
			stop = controlloVincita(matrice);
			if (stop) {
				break;
			}
			//Cambio giocatore
			if (giocatore == 'X') {
				giocatore= 'O';
			} else {
				giocatore = 'X';
			}
		}
		//Visualizzazione della vittoria
		visualizza(matrice);
		System.out.println(giocatore + " Ha vinto!");
	}
	
	public static int[] posizione(int pos) {
		int[] xy = new int[3];
		// Mi vergogno a scrivere di un codice del genere ma non ho idea di come scriverlo altrimenti
		switch (pos) {
			case 1:
				xy[0] = 0;
				xy[1] = 0;
				break;
			case 2:
				xy[0] = 0;
				xy[1] = 1;
				break;
			case 3:
				xy[0] = 0;
				xy[1] = 2;
				break;
			case 4:
				xy[0] = 1;
				xy[1] = 0;
				break;
			case 5:
				xy[0] = 1;
				xy[1] = 1;
				break;
			case 6:
				xy[0] = 1;
				xy[1] = 2;
				break;
			case 7:
				xy[0] = 2;
				xy[1] = 0;
				break;
			case 8:
				xy[0] = 2;
				xy[1] = 1;
				break;
			case 9:
				xy[0] = 2;
				xy[1] = 2;
				break;
		}
		
		return xy;
	}
	
	public static boolean controlloVincita(char[][] matrice) {
		
		boolean risultato=false;
		
		//Controllo orizzontale
		// utilizzo matrice[i][j] == matrice[i][j+1] && matrice[i][j]==matrice[i][j+2]
		int j = 0;
		for (int i = 0; i <matrice.length; i++) {
			
			risultato = matrice[i][j]==(matrice[i][j+1]) && matrice[i][j]==matrice[i][j+2];
			if (risultato) { // In modo che una volta che risultato è true, esso viene passato al return
				break;
			}
		}
		
		// Una volta controllato orizzontalmente, si controlla verticalmente
		for (int i = 0; i <matrice.length; i++) {
			
			risultato = matrice[j][i]==(matrice[j+1][i]) && matrice[j][i]==matrice[j+2][i];
			if (risultato) {
				break;
			}
		}
		
		// Ora obliqui
		if (matrice[0][0]==matrice[1][1] && matrice[0][0]==matrice[2][2]) {
			risultato = true;
		}
		if (matrice[0][2]==matrice[1][1] && matrice[0][2]==matrice[2][0]) {
			risultato = true;
		}
		
		return risultato; //Che palle, return si puo fare solo nello scope della funzione
	}
	public void appunti() {
	//Si fanno le funzioni per quelle cose che vengono richieste più volte
	
	// gioco() contine la matrice e visualizza matrice prima ogni mossa
	
	// mentre controllovincita è falso 
	// visualizzo la matrice per mostrare le mosse possibili all'utente
	// chiedo posizione numerica 1 a 9:
	// out("Inserisci posizione ")
	// la passo dentro la funzione posizione()
	// dentro posizione() switch case 1 a 9 che ritorna un array
	// la posizione ritornata deve essere convertita in 2 valori x e y estrapolandoli dall'array
	// char giocatore = 'X'
	// le mosse si gestiscono con un if (if giocatore == 'x')
	// dentro if so chi è il giocatore (metto giocatore = 'O')
	// 
	// per cambiare la matrice serve di sapere di chi è il turno e la posizione (matrice[x][y] = giocatore)
	// ad ogni mossa bisogna cambiare la matrice 
	// controllo condizioni di vincita controlloVincita(matrice)
	// 
	// se ritorna true, break, out(giocatore ha vinto)
	// 
	}
	
	
	public static void visualizza(char[][] matrice) {
		//Visualizzazione matrice numeri
		
		for (int i = 0; i <matrice.length; i++) {
			
			System.out.println("-------------");
			System.out.print("| ");
			
			char[] row = matrice[i];
			
			for (int j = 0; j <row.length; j++) {
				
				System.out.print(matrice[i][j]);
				System.out.print(" | ");
			}
			System.out.println("");
		}
		System.out.println("-------------");
	}
}
