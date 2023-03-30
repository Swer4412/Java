package it.jac.corsojava.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Fotografia {
	
	private int id;
	
	private String nome;
	
	private String formato;
	
	private double dimensione;
	
	private ArrayList<Proprieta> proprieta;
	
	private String utenteCreazione;

	private LocalDateTime dataCreazione;
	//Non metto utente e data modifica dato che la consegna non richiede modifiche
	
	public Fotografia() {}
	
	public Fotografia(int id, String nome, String formato, double dimensione, 
			String utenteCreazione, LocalDateTime dataCreazione) {
		
		this.id = id;
		this.nome = nome;
		this.formato = formato;
		this.dimensione = dimensione;
		this.utenteCreazione = utenteCreazione;
		this.dataCreazione = dataCreazione;
	}
	
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
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public double getDimensione() {
		return this.dimensione;
	}
	public void setDimensione(double dimensione) {
		this.dimensione = dimensione;
	}
	public ArrayList<Proprieta> getProprieta() {
		return proprieta;
	}
	public void setProprieta(ArrayList<Proprieta> proprieta) {
		this.proprieta = proprieta;
	}
	public String getUtenteCreazione() {
		return utenteCreazione;
	}
	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}
	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
}


