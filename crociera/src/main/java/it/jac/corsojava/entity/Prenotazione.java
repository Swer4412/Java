package it.jac.corsojava.entity;

import java.util.ArrayList;

// rappresenta l'entità da archiviare
public class Prenotazione {
	
	private int id;
	private int durataViaggio;
	private ArrayList<Persona> persone;
	private TipoCabina cabina;
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDurataViaggio() {
		return this.durataViaggio;
	}
	public void setDurataViaggio(int durataViaggio) {
		this.durataViaggio = durataViaggio;
	}
	public ArrayList<Persona> getPersone() {
		return this.persone;
	}
	public void setPersone(ArrayList<Persona> persone) {
		
	}
	public TipoCabina getCabina() {
		return this.cabina;
	}
	public void setCabina(TipoCabina cabina) {
		this.cabina = cabina;
	}
}


