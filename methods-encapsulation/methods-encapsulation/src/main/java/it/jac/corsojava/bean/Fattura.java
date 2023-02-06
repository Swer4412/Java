package it.jac.corsojava.bean;

import java.time.LocalDate;

public class Fattura {

	private String numeroFattura;
	private LocalDate dataFattura;
	private String cliente;
	private double importo;
	
	public Fattura(int anno, int numero) {

//		attraverso il costruttore imposto in modo sicuro il numero fattura e inizializzo la data con la giornata odierna
		this.numeroFattura = numero + "/" + anno;
		this.dataFattura = LocalDate.now();
	}
	
	public String getNumeroFattura() {
		return numeroFattura;
	}
	
	public void setDataFattura(LocalDate dataFattura) {
		if (!dataFattura.isAfter(LocalDate.now())) {
			this.dataFattura = dataFattura;
		}
	}
	
	public LocalDate getDataFattura() {
		return dataFattura;
	}
	
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	public String getCliente() {
		return cliente;
	}
	
	public void setImporto(double importo) {
//		con il metodo posso controllare i valori in input e decidere se registrare il dato in input o meno
		if (importo > 0) {
			this.importo = importo;
		}
	}
	
	public double getImporto() {
		return importo;
	}

	@Override
	public String toString() {
		return "Fattura [numeroFattura=" + numeroFattura + ", dataFattura=" + dataFattura + ", cliente=" + cliente
				+ ", importo=" + importo + "]";
	}
	
}
