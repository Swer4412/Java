package it.jac.mvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.dto.DaysDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/now")
public class NowController extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(NowController.class);

	public NowController() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		log.info("Ricevuta richiesta doGet");
		
		//Prendo i parametri passati nella querystring ?start_date=1212-02-12&num_days=12
		String parStartDate = request.getParameter("start_date");
		String parNumDays = request.getParameter("num_days");
		
		//Metto questi 2 attributi nel parametro richiesta
		request.setAttribute("par_start_day", parStartDate);
		request.setAttribute("par_num_days", parNumDays);
		
		//Determino il formattatore della data
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		//Variabili default, mi servono in caso l'utente inserisca male o niente
		LocalDate startDate = LocalDate.now();
		int numDays = 1;
		String errorMessage = "";
		
		try {
			if (parStartDate != null) {
				startDate = LocalDate.parse(parStartDate, dtf);
			}
			if (parNumDays != null ) {
				numDays = Integer.parseInt(parNumDays);
			}
		} catch (NumberFormatException e) {
			errorMessage= "Data non valida";
		} catch (DateTimeParseException e) {
			errorMessage= "Numero giorni non valido";
		}
		
		List<DaysDto> list = new ArrayList<>();
		
		LocalDate refDay = startDate;
		int i = 1;
		
		do {
		
		String day = dtf.format(refDay);
	
		String dayOfWeek = refDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
		
		String dayOfMonth = String.valueOf(refDay.getDayOfMonth());
		
		String dayOfYear = String.valueOf(refDay.getDayOfYear());
		
		DaysDto dto = new DaysDto ();
		dto.setDay(day);
		dto.setDayOfWeek(dayOfWeek);
		dto.setDayOfMonth(dayOfMonth);
		dto.setDayOfYear(dayOfYear);
		
		list.add(dto);
		
		refDay = refDay.plusDays(1);
		
		} while (i++ < numDays);
		
		request.setAttribute("errorMessage", errorMessage);
		
		request.setAttribute("list", list);

		request.getRequestDispatcher("/now.jsp").forward(request, response);
	}

}

