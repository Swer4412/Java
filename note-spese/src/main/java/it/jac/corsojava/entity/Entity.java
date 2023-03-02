package it.jac.corsojava.entity;

import java.util.ArrayList;

// rappresenta l'entità da archiviare
public class Entity {
	
	private Societa societa;
	
	private Dipendente dipendente;
	
	private NotaSpesa nota_spesa;
	
	private ArrayList<VoceSpesa> voci_spesa;
	
	private CategoriaSpesa categoria_spesa;

	public Societa getSocieta() {
		return societa;
	}

	public void setSocieta(Societa societa) {
		this.societa = societa;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public NotaSpesa getNota_spesa() {
		return nota_spesa;
	}

	public void setNota_spesa(NotaSpesa nota_spesa) {
		this.nota_spesa = nota_spesa;
	}

	public ArrayList<VoceSpesa> getVoci_spesa() {
		return voci_spesa;
	}

	public void setVoci_spesa(ArrayList<VoceSpesa> voci_spesa) {
		this.voci_spesa = voci_spesa;
	}

	public CategoriaSpesa getCategoria_spesa() {
		return categoria_spesa;
	}

	public void setCategoria_spesa(CategoriaSpesa categoria_spesa) {
		this.categoria_spesa = categoria_spesa;
	}

	
	
}
