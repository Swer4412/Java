package it.jac.db.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotaSpesa {

	private int id;

	private String cod;

	private String meseRif;

	private Double importoTotale;

	private String stato;

	private String utenteCreazione;

	private LocalDateTime dataCreazione;

	private String utenteModifica;

	private LocalDateTime dataModifica;

	private Dipendente dipendente;

	private List<VoceSpesa> vociSpesa = new ArrayList<>();

	public NotaSpesa() {
		
	}
	
	public NotaSpesa(int id, String cod, String meseRif, double importoTotale, String stato, 
			String utenteCreazione, LocalDateTime dataCreazione,
			String utenteModifica, LocalDateTime dataModifica) {
		
		this.id = id;
		this.cod = cod;
		this.meseRif = meseRif;
		this.importoTotale = importoTotale;
		this.stato = stato;
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

	public String getMeseRif() {
		return meseRif;
	}

	public void setMeseRif(String meseRif) {
		this.meseRif = meseRif;
	}

	public Double getImportoTotale() {
		return importoTotale;
	}

	public void setImportoTotale(Double importoTotale) {
		this.importoTotale = importoTotale;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
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

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public List<VoceSpesa> getVociSpesa() {
		return vociSpesa;
	}

	public void setVociSpesa(List<VoceSpesa> vociSpesa) {
		this.vociSpesa = vociSpesa;
	}

}