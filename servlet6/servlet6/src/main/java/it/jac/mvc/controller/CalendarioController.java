package it.jac.mvc.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.dto.DateInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/calendar")
public class CalendarioController extends HttpServlet {

	private static Logger log = LogManager.getLogger(CalendarioController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parStartDate = req.getParameter("ref");
		
		LocalDate date = LocalDate.now().withDayOfMonth(1);
		
		try {
			if (parStartDate != null && parStartDate.length() == 6) {
				int year = Integer.parseInt(parStartDate.substring(0, 4));
				int month = Integer.parseInt(parStartDate.substring(4));
			
				date = LocalDate.of(year, month, 1);
			}
		} catch(NumberFormatException e) {
			// do nothing, use default now date
		}

		LocalDate datePrec = date.minusMonths(1);
		LocalDate dateSucc = date.plusMonths(1);
		String mesePrec = datePrec.format(DateTimeFormatter.ofPattern("yyyyMM"));
		String labelMesePrec = datePrec.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
		String meseSucc = dateSucc.format(DateTimeFormatter.ofPattern("yyyyMM"));
		String labelMeseSucc = dateSucc.getMonth().getDisplayName(TextStyle.FULL, Locale.ITALIAN);;

		log.debug("date from param {}", date);

		LocalDate dateLimit = date.plusMonths(1); 
		
		while (date.getDayOfWeek() != DayOfWeek.MONDAY) {
			
			date = date.minusDays(1);
		}
		log.debug("date after while {}", date);
		
		List<DateInfo> list = new ArrayList<>();
		do {
			
			list.add(new DateInfo(date));
			date = date.plusDays(1);
			
		} while (date.isBefore(dateLimit) || date.getDayOfWeek() != DayOfWeek.MONDAY);
		
		log.debug("date after all {}", date);
		
		req.setAttribute("dateList", list);
		req.setAttribute("mesePrec", mesePrec);
		req.setAttribute("labelMesePrec", labelMesePrec);
		req.setAttribute("meseSucc", meseSucc);
		req.setAttribute("labelMeseSucc", labelMeseSucc);
		
		
		req.getRequestDispatcher("calendar.jsp").forward(req, resp);
	}
}
