package it.jac.corsojava.entity;

import java.time.LocalDateTime;

public class Societa {
	
	private int id;
	
	private String cod;
	
	private String denominazione;
	
	private String utente_creazione;
	
	private LocalDateTime data_creazione;

	private String utente_modifica;
	
	private LocalDateTime data_modifica;

	
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

	public String getUtente_creazione() {
		return utente_creazione;
	}

	public void setUtente_creazione(String utente_creazione) {
		this.utente_creazione = utente_creazione;
	}

	public LocalDateTime getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}

	public String getUtente_modifica() {
		return utente_modifica;
	}

	public void setUtente_modifica(String utente_modifica) {
		this.utente_modifica = utente_modifica;
	}

	public LocalDateTime getData_modifica() {
		return data_modifica;
	}

	public void setData_modifica(LocalDateTime data_modifica) {
		this.data_modifica = data_modifica;
	}
	
	
}
