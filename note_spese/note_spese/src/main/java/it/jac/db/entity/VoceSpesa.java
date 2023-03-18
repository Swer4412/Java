package it.jac.db.entity;

import java.time.LocalDateTime;

public class VoceSpesa {

	private int id;

	private String commento;

	private double importo;

	private String utenteCreazione;

	private LocalDateTime dataCreazione;

	private String utenteModifica;

	private LocalDateTime dataModifica;

	private NotaSpesa notaSpesa;

	private Categoria categoria;

	public VoceSpesa() {
		
	}
	
	public VoceSpesa(int id, String commento, double importo, 
			String utenteCreazione, LocalDateTime dataCreazione,
			String utenteModifica, LocalDateTime dataModifica) {
		
		this.id = id;
		this.commento = commento;
		this.importo = importo;
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

	public String getCommento() {
		return commento;
	}

	public void setCommento(String commento) {
		this.commento = commento;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
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

	public NotaSpesa getNotaSpesa() {
		return notaSpesa;
	}

	public void setNotaSpesa(NotaSpesa notaSpesa) {
		this.notaSpesa = notaSpesa;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}