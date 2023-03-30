package it.jac.corsojava.entity;

import java.time.LocalDateTime;

public class Proprieta {
	
	private int id;
	
	private String chiave;
	
	private String valore;
	
	private Fotografia fotografia;
	
	private String utenteCreazione;

	private LocalDateTime dataCreazione;
	//Non metto utente e data modifica dato che la consegna non richiede modifiche
	
	public Proprieta() {}
	
	public Proprieta(int id, String chiave, String valore,
			String utenteCreazione, LocalDateTime dataCreazione) {
		
		this.id = id;
		this.chiave = chiave;
		this.valore = valore;
		this.utenteCreazione = utenteCreazione;
		this.dataCreazione = dataCreazione;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChiave() {
		return chiave;
	}
	public void setChiave(String chiave) {
		this.chiave = chiave;
	}
	public String getValore() {
		return valore;
	}
	public void setValore(String valore) {
		this.valore = valore;
	}
	public Fotografia getFotografia() {
		return fotografia;
	}
	public void setFotografia(Fotografia fotografia) {
		this.fotografia = fotografia;
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
}
