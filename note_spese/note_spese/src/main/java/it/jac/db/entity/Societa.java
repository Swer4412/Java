package it.jac.db.entity;

import java.time.LocalDateTime;

public class Societa {

	private int id;

	private String cod;

	private String denominazione;

	private String utenteCreazione;

	private LocalDateTime dataCreazione;

	private String utenteModifica;

	private LocalDateTime dataModifica;

	public Societa() {
		
	}
	
	public Societa(int id, String cod, String denominazione, 
			String utenteCreazione, LocalDateTime dataCreazione,
			String utenteModifica, LocalDateTime dataModifica) {
		
		this.id = id;
		this.cod = cod;
		this.denominazione = denominazione;
		this.utenteCreazione = utenteCreazione;
		this.dataCreazione = dataCreazione;
		this.utenteModifica = utenteModifica;
		this.dataModifica = dataModifica;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
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

	public String getUtenteModifica() {
		return utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	public LocalDateTime getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(LocalDateTime dataModifica) {
		this.dataModifica = dataModifica;
	}

}
