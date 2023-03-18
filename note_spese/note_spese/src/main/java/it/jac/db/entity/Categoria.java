package it.jac.db.entity;

import java.time.LocalDateTime;

public class Categoria {

	private int id;

	private String cod;

	private String descrizione;

	private String utenteCreazione;

	private LocalDateTime dataCreazione;

	private String utenteModifica;

	private LocalDateTime dataModifica;

	public Categoria() {
		
	}
	
	public Categoria(int id, String cod, String descrizione, 
			String utenteCreazione, LocalDateTime dataCreazione,
			String utenteModifica, LocalDateTime dataModifica) {
		
		this.id = id;
		this.cod = cod;
		this.descrizione = descrizione;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
