package it.jac.mvc.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity { //abstract serve per indicare che tale oggetto non si può instanzializzare
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "data_creazione")
	private LocalDateTime dataCreazione;
	
	@Column(name = "utente_creazione")
	private String utenteCreazione;
	
	@Column(name = "data_modifica")
	private LocalDateTime dataModifica;
	
	@Column(name = "utente_modifica")
	private String utenteModifica;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getUtenteCreazione() {
		return utenteCreazione;
	}
	public void setUtenteCreazione(String utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}
	public LocalDateTime getDataModifica() {
		return dataModifica;
	}
	public void setDataModifica(LocalDateTime dataModifica) {
		this.dataModifica = dataModifica;
	}
	public String getUtenteModifica() {
		return utenteModifica;
	}
	public void setUtenteModifica(String utenteModifica) {
		this.utenteModifica = utenteModifica;
	}
	
	
}
