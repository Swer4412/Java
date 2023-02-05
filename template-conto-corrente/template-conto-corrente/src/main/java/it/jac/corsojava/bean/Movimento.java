package it.jac.corsojava.bean;

public class Movimento {

	public static final int TIPO_DEPOSITO = 0;
	public static final int TIPO_PRELIEVO = 1;
	public static final int TIPO_RICARICA = 2;
	
	private double valore;
	
//	0 - deposito
//	1 - prelievo
//	2 - ricarica
	private int tipologia;

	public double getValore() {
		return valore;
	}

	

	public void setValore(double valore) {
		this.valore = valore;
	}

	public int getTipologia() {
		return tipologia;
	}

	public void setTipologia(int tipologia) {
		this.tipologia = tipologia;
	}
	
	@Override
	public String toString() {
		return "Movimento [valore=" + valore + ", tipologia=" + tipologia + "]";
	}
}
