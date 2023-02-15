package it.jac.corsojava.entity;

import java.util.HashMap;

// rappresenta l'entità da archiviare
public class Fotografia {
	
	private int id;
	private String nome;
	private FormatoFoto formato;
	private double dimensione;
	private HashMap<String, String> proprieta;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public FormatoFoto getFormato() {
		return this.formato;
	}
	public void setFormato(FormatoFoto formato) {
		this.formato = formato;
	}
	public double getDimensione() {
		return this.dimensione;
	}
	public void setDimensione(double dimensione) {
		this.dimensione = dimensione;
	}
	public HashMap<String, String> getProprieta() {
		return this.proprieta;
	}
	public void setProprieta(HashMap<String, String> proprieta) {
		this.proprieta = proprieta;
	}
	
	
	
}


