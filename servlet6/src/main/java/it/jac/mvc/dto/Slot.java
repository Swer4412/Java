package it.jac.mvc.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Slot {
	
	private int id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean prenotato;
	
	public Slot(int id, LocalDate date, LocalTime startTime, LocalTime endTime, boolean prenotato) {
		super();
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.prenotato = prenotato;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public boolean isPrenotato() {
		return prenotato;
	}
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}
}
