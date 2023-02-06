package it.jac.corsojava.bean;

import java.time.LocalDate;

public class FatturaErrata {

	public String numeroFattura;
	public LocalDate dataFattura;
	public String cliente;
	public double importo;

	@Override
	public String toString() {
		return "FatturaErrata [numeroFattura=" + numeroFattura + ", dataFattura=" + dataFattura + ", cliente=" + cliente
				+ ", importo=" + importo + "]";
	}
	
}
