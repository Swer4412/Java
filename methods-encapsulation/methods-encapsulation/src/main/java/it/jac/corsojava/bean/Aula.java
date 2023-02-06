package it.jac.corsojava.bean;

public class Aula {

//	� una best practice quella di nominare le costanti tutte in maiuscolo e usando il carattere '_' per separare le parole
	public static final int MAX_POSTI; //Costante
	//Final fa in modo che il valore una volta impostato, non cambi più durante l'esecuzione
//	una variabile costante deve sempre essere inizializzata con un valore
//	public static final int MIN_POSTI; // non compila!
	public static final int MIN_POSTI = 5;
	
	static {
		MAX_POSTI = 40; //Ora MAX_POSTI è sempre 40
	}
	
	public static int postiTotali; //Pericoloso dichiararlo public perchè puo accettare valori altissimi e negativi (Aula.postiTotali)
	
	private String nome;
	private int postiOccupati;
	private int postiLiberi;
	
	public void configura(String nome, int postiLiberi) {
		this.nome = nome;
		this.postiLiberi = postiLiberi;
	}
	
	public void occupaPosti(int numeroPosti) {
		
		this.postiOccupati += numeroPosti;
		this.postiLiberi -= numeroPosti;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getPostiDisponibili() {
		return this.postiLiberi - this.postiOccupati;
	}
}
