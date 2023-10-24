package it.jac.mvc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Slot extends BaseEntity{

	private int id;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private boolean prenotato;
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
