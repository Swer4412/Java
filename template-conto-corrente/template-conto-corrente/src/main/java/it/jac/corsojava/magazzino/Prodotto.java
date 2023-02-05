package it.jac.corsojava.magazzino;

public class Prodotto {
	
	private int codice = 0;
	private String desc;
	private double prezzo;
	private String stato;
	
	public Prodotto(String desc, double prezzo, int codice) {
		this.desc=desc;
		this.prezzo=prezzo;
		this.codice=codice;
		this.stato="IN MAGAZZINO";
	}
	
	public int getCodice() {
		return this.codice;
	}
	

	public String getDesc() {
		return desc;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public String getStato() {
		return stato;
	}
	
	public void setStato(String stato) {
		this.stato=stato;
	}
	
	
	
}
