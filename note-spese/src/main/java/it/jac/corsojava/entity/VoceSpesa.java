package it.jac.corsojava.entity;

import java.time.LocalDateTime;

public class VoceSpesa {
	
	private int id;
	
	private String commento;
	
	private double importo;
	
	private NotaSpesa nota_spesa;
	
	private CategoriaSpesa categoria_spesa;
	
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

	public NotaSpesa getNota_spesa() {
		return nota_spesa;
	}

	public void setId_nota_spesa(NotaSpesa nota_spesa) {
		this.nota_spesa = nota_spesa;
	}

	public CategoriaSpesa getCategoria_spesa() {
		return categoria_spesa;
	}

	public void setCategoria_spesa(CategoriaSpesa categoria_spesa) {
		this.categoria_spesa = categoria_spesa;
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
