package it.jac.mvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
		
		//CONTROLLO NULLI
		if (month == null) {
			monthNum = LocalDate.now().getMonthValue();
		} else {
			monthNum = Integer.getInteger(month);
		}
		if (year == null) {
			yearNum = LocalDate.now().getYear();
		} else {
			yearNum = Integer.getInteger(year);
		}
		
		//Prendo la prima data del mese in base a cosa ho ottenuto dall'url
		LocalDate firstDate = LocalDate.of(yearNum, monthNum, 1);
		
		int monthDaysNum = firstDate.getMonth().length(true);
		
		
		req.setAttribute("daysList", daysList);
		
		req.getRequestDispatcher("calendar.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doPost");		
	}

}
