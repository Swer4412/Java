package it.jac.mvc.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity { //abstract serve per indicare che tale oggetto non si può instanzializzare
	
	private int id;
	private LocalDateTime dataCreazione;
	private String utenteCreazione;
	private LocalDateTime dataModifica;
	private String utenteModifica;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getUtenteCreazione() {
		return utenteCreazione;
	}
	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}
	public LocalDateTime getDataModifica() {
		return dataModifica;
	}
	public void setDataModifica(LocalDateTime dataModifica) {
		this.dataModifica = dataModifica;
	}
	public String getUtenteModifica() {
		return utenteModifica;
	}
	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}
	
	
}
