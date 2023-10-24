package it.jac.mvc.dto;

import java.time.LocalDate;

public class DateInfo {

	private LocalDate date;
	private String dateFormatted;
	
	public DateInfo(LocalDate date) {
		this.date = date;
	}

	public String getDateFormatted() {
		return dateFormatted;
	}

	public void setDateFormatted(String dateFormatted) {
		this.dateFormatted = dateFormatted;
	}

	public LocalDate getDate() {
		return date;
	}
	
}
