package it.jac.db.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Dipendente {

	private int id;

	private String matricola;

	private String nome;

	private String cognome;

	private LocalDate dataNascita;

	private String utenteCreazione;

	private LocalDateTime dataCreazione;

	private String utenteModifica;

	private LocalDateTime dataModifica;

	private Societa societa;

	public Dipendente() {
		
	}
	
	public Dipendente(int id, String matricola, String nome, String cognome, LocalDate dataNascita, 
			String utenteCreazione, LocalDateTime dataCreazione,
			String utenteModifica, LocalDateTime dataModifica) {
		
		this.id = id;
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
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

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
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

	public Societa getSocieta() {
		return societa;
	}

	public void setSocieta(Societa societa) {
		this.societa = societa;
	}

}
