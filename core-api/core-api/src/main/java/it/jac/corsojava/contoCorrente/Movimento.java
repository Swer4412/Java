package it.jac.corsojava.contoCorrente;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movimento {
	//Definisco propietà
	private static int numMovimenti = 0;
	private static int saldo = 0;
	public String desc;
	public int somma; 
	public LocalDate data;
	public LocalTime ora;
	
	//Dichiaro il costruttore
	public Movimento (int somma) {
		//Utilizzo le proprietà degli oggetti per semplificarmi la vita
		numMovimenti++;
		saldo+=somma;
		
		//Assegno la descrizione in base a quanto è la somma
		if (somma>0) {
			this.desc="Deposito";
		} else {
			this.desc="Prelievo";
		}
		//Assegno le altre variabili
		this.somma=somma;
		this.data=LocalDate.now();
		this.ora=LocalTime.now();
	}
	
	public static int getSaldo() {
		return saldo;
	}
}
