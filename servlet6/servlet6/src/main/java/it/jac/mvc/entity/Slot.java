package it.jac.mvc.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "slot")
public class Slot extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;
	
	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;
	
	@Column(name = "prenotato")
	private boolean prenotato;
	
	@Column(name = "risorsa")
	private Risorsa risorsa;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	public boolean isPrenotato() {
		return prenotato;
	}
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}
	public Risorsa getRisorsa() {
		return risorsa;
	}
	public void setRisorsa(Risorsa risorsa) {
		this.risorsa = risorsa;
	}

	
}
