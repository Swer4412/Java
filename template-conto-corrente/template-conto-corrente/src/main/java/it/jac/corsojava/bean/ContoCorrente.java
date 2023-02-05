package it.jac.corsojava.bean;

import java.util.ArrayList;

public class ContoCorrente {

	private double saldo;
	private ArrayList<Movimento> movimenti = new ArrayList<>();
	
	public void deposito(double valore) {
		
		Movimento mov = new Movimento(); //Nuovo movimento
		
		mov.setValore(valore);
		mov.setTipologia(Movimento.TIPO_DEPOSITO);
		
		this.movimenti.add(mov); // Inserisco nella arraylist dell'oggetto 
		
		this.saldo += valore;
	}

	public boolean prelievo(double valore) {
		
		if (!checkSaldo(valore)) {
			return false;
		}
			Movimento mov = new Movimento();
			
			mov.setValore(valore);
			mov.setTipologia(Movimento.TIPO_PRELIEVO);
			
			this.movimenti.add(mov);
			
			this.saldo += valore;
			
			return true;
			
	}

	public boolean ricarica(String numero, double valore) {
		
		return true;
	}

	public ArrayList<Movimento> getElencoMovimenti() {
			
		return this.movimenti;
	}
	
	public double getSaldo() {
		return this.saldo;
	}

	private boolean checkSaldo(double valore) {
		
		return this.saldo >= valore;
	}
	
}
