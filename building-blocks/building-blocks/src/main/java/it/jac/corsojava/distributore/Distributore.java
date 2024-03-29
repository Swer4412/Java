package it.jac.corsojava.distributore;

public class Distributore {
	
	private String codice; //Dichiarate private perche sono di uso di questa classe
	private int acquaNaturale = 10;
	private int merendine = 10;
	private int the = 10;
	
	public boolean prelevaAcqua() {
		this.acquaNaturale--; //this fa riferimento alle caratteristiche dell'oggetto(self in python)
		return true;
	}
	public boolean prelevaMerendina() {
		this.merendine--; 
		return true;
	}
	public boolean prelevaThe() {
		this.the--; 
		return true;
	}
	
	public boolean preleva(int acqua, int merendine, int the) {
			this.acquaNaturale = this.acquaNaturale - acqua;
			this.merendine = this.merendine - merendine;
			this.the = this.the - the;
			
		return true;
	}
	
	public void printInfo(String colore) {
		String info;
		info = String.format("%20s | %20s | %20s | %20s |", this.codice, this.acquaNaturale, this.merendine, this.the );
		// 20s fa uno spazio di 20 caratteri compresa la variabile(%)
		System.out.println(colore + info);
	}
	
	// Codice generato con click destro, source, generate getters and setters
	public String getCodice() { //Dato che le variabili le abbiamo dichiarate private, per condividerle bisogna fare una funzione così
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice; // this.codice vuol dire codice dell oggetto = codice, cioè quello passato come args
	}
	public int getAcquaNaturale() {
		return acquaNaturale;
	}
	public int getMerendine() {
		return merendine;
	}
	public int getThe() {
		return the;
	}
}
