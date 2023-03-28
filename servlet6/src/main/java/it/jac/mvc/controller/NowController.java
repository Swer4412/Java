package it.jac.mvc.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/date")
public class NowController extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(NowController.class);

	public NowController() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doGet");
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.S");
		
		String nowFormatted = dtf.format(now);
		
		//Prendo il giorno della settimana; traducol tale giorno della settimana in italiano
		String dayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
		
		String dayOfMonth = String.valueOf(now.getDayOfMonth());
	
		String dayOfYear = String.valueOf(now.getDayOfYear());
		
		//Salvo in request diversi attributi, questi attributi poi li passo alla JSP
		request.setAttribute("day", nowFormatted); 
		request.setAttribute("day_of_week", dayOfWeek); //Creo attributo "day_of_week" e ci passo dentro il day of week
		request.setAttribute("day_of_month", dayOfMonth);
		request.setAttribute("day_of_year", dayOfYear);
		
		request.getRequestDispatcher("/now.jsp").forward(request, response);
	}

}

