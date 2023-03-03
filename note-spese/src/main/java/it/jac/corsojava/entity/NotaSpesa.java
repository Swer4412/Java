package it.jac.corsojava.entity;

import java.time.LocalDateTime;

public class NotaSpesa {
	
	private int id;
	
	private String cod;
	
	private String mese_rif;
	
	private double importo_totale;
	
	private StatoSpesa stato;
	
	private int id_dipendente;
	
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

	public String getMese_rif() {
		return mese_rif;
	}

	public void setMese_rif(String mese_rif) {
		this.mese_rif = mese_rif;
	}

	public double getImporto_totale() {
		return importo_totale;
	}

	public void setImporto_totale(double importo_totale) {
		this.importo_totale = importo_totale;
	}

	public StatoSpesa getStato() {
		return stato;
	}

	public void setStato(StatoSpesa stato) {
		this.stato = stato;
	}

	public int getId_dipendente() {
		return id_dipendente;
	}

	public void setId_dipendente(int id_dipendente) {
		this.id_dipendente = id_dipendente;
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
