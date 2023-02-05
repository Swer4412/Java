package it.jac.corsojava.contoCorrente;

public interface CCInterfaccia {
	
	void deposita(int num);
	String preleva(int num);
	String getMovimenti(String datain);
	int getSaldo();
	boolean ricaricaTel(int num);
	
}
