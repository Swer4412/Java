package it.jac.mvc.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calendar") 
public class CalendarServlet extends HttpServlet { 
	
	private Logger log = LogManager.getLogger(CalendarServlet.class);

	public CalendarServlet() {}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<LocalDate> daysList = new ArrayList<LocalDate>();
		
		String month = req.getParameter("month");
		String year = req.getParameter("year");
		
		int monthNum;
		int yearNum;
		
		
		//CONTROLLO NULLI E SE è UN NUMERO PULITO
		if (month == null || !month.matches("[0-9]+")) { 
			monthNum = LocalDate.now().getMonthValue();
		} else {
			monthNum = Integer.parseInt(month);
		}
		if (year == null || !year.matches("[0-9]+")) {
			yearNum = LocalDate.now().getYear();
		} else {
			yearNum = Integer.parseInt(year);
		}
		
		//CONTROLLO NUMERI ILLEGALI
		if (monthNum > 12) {
			monthNum = 1;
			yearNum = yearNum + 1;
		}
		if (monthNum < 1) {
			monthNum = 12;
			yearNum = yearNum - 1;
		}
		
		//PRENDO PRIMO E ULTIMO GIORNO DEL CALENDARIO
		//Prendo la prima data del mese in base a cosa ho ottenuto dall'url
		LocalDate firstMonthDay = LocalDate.of(yearNum, monthNum, 1);
		
		//Prendo valori per interfaccia
		Month currentMonth = firstMonthDay.getMonth();
		Month previousMonth = firstMonthDay.minusMonths(1).getMonth();
		Month nextMonth = firstMonthDay.plusMonths(1).getMonth();
		
		LocalDate firstCalendarDay = firstMonthDay; //Creo una copia
		
		//Prendo la prima data del calendario
		while (firstCalendarDay.getDayOfWeek() != DayOfWeek.MONDAY) {
			firstCalendarDay = firstCalendarDay.minusDays(1);
		}
		
		LocalDate lastCalendarDay = firstMonthDay.with(TemporalAdjusters.lastDayOfMonth());
		
		while (lastCalendarDay.getDayOfWeek() != DayOfWeek.SUNDAY) {
			lastCalendarDay = lastCalendarDay.plusDays(1);
		}
		
		//RIEMPIO LA LISTA CON I GIORNI DEL CALENDARIO
		long daysNum = ChronoUnit.DAYS.between(firstCalendarDay, lastCalendarDay);
		
		for (int i = 0; i <= daysNum ;i++) {
			daysList.add(firstCalendarDay);
			firstCalendarDay = firstCalendarDay.plusDays(1);
		}
		
		req.setAttribute("daysList", daysList);
		req.setAttribute("yearNum", yearNum);
		req.setAttribute("monthNum", monthNum);
		req.setAttribute("currentMonth", currentMonth);
		req.setAttribute("previusMonth", previousMonth);
		req.setAttribute("nextMonth", nextMonth);
		
		req.getRequestDispatcher("calendar.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doPost");		
	}

}
