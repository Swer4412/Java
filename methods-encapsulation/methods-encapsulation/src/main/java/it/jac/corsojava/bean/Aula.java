package it.jac.corsojava.bean;

public class Aula {

//	è una best practice quella di nominare le costanti tutte in maiuscolo e usando il carattere '_' per separare le parole
	public static final int MAX_POSTI;
	
//	una variabile costante deve sempre essere inizializzata con un valore
//	public static final int MIN_POSTI; // non compila!
	public static final int MIN_POSTI = 5;
	
	static {
		MAX_POSTI = 40;
	}
	
	public static int postiTotali;
	
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
