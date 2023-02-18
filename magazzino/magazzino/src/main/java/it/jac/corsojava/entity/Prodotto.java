package it.jac.corsojava.entity;

import java.time.LocalDateTime;

// rappresenta l'entità da archiviare
public class Prodotto {

	// identificativo del prodotto
	private int id;

	// codice del prodotto
	private String cod;

	// descrizione del prodotto
	private String descrizione;

	// prezzo del prodotto
	private double prezzo;

	// stato del prodotto
	private StatoProdotto stato;

	private String utenteCreazione;

	private String utenteModifica;

	private LocalDateTime dataCreazione;

	private LocalDateTime dataModifica;

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

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public StatoProdotto getStato() {
		return stato;
	}

	public void setStato(StatoProdotto stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", cod=" + cod + ", descrizione=" + descrizione + ", prezzo=" + prezzo
				+ ", stato=" + stato + "]";
	}

	public String getUtenteCreazione() {
		return utenteCreazione;
	}

	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}

	public String getUtenteModifica() {
		return utenteModifica;
	}

	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public LocalDateTime getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(LocalDateTime dataModifica) {
		this.dataModifica = dataModifica;
	}

}
