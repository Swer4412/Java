package it.jac.corsojava.entity;

import java.util.ArrayList;

// rappresenta l'entità da archiviare
public class Prenotazione {
	
	private int id;
	private int durataViaggio;
	private ArrayList<Persona> persone; //ArrayList che contiene oggetti di tipo Persona
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
		this.persone = persone;
	}
	public TipoCabina getCabina() {
		return this.cabina;
	}
	public void setCabina(String cabina) {
		switch (cabina) {
		case "interna":
			this.cabina=TipoCabina.INTERNA;
			break;
		case "esterna":
			this.cabina=TipoCabina.ESTERNA;
			break;
		case "balcone":
			this.cabina=TipoCabina.CON_BALCONE;
			break;
		case "suite":
			this.cabina=TipoCabina.SUITE;
			break;
		}
	}
}


