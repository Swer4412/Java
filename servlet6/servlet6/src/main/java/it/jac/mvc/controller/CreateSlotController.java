package it.jac.mvc.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.entity.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/create-slot")
public class CreateSlotController extends HttpServlet {
	
	private static Logger log = LogManager.getLogger(CreateSlotController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		if (list == null) {

			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		req.setAttribute("action", "create-slot");

		req.getRequestDispatcher("detail-slot.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Leggo i parametri inviati dalla post
		String parRisorsa = req.getParameter("risorsa");
		String parStartTime = req.getParameter("start_time");
		String parEndTime = req.getParameter("end_time");
		
		log.debug("Parametri [risorsa={}, date={}, startTime={}, endTime={}", parRisorsa, parStartTime, parEndTime);
		
		List<String> errorList = new ArrayList<String>();
		
		if (parRisorsa == null) {
			errorList.add("Risorsa non specificata");
		}
		if (parStartTime == null) {
			errorList.add("Ora di inizio non specificata");
		}
		if (parEndTime == null) {
			errorList.add("Ora di fine non specificata");
		}
		
		Slot slot = new Slot();
		try {
			slot.setStartDateTime(LocalDateTime.parse(parStartTime));
			slot.setEndDateTime(LocalDateTime.parse(parEndTime));
			slot.setPrenotato(false);
		} catch (DateTimeParseException e) {
			log.error("errore durante il parsing date", e);
			errorList.add("Date/orari errati, controllare il formato");
		}
		
		if (!errorList.isEmpty()) {
			req.setAttribute("error_list", errorList);
			req.setAttribute("risorsa", parRisorsa);
			req.setAttribute("oraInizio", parStartTime);
			req.setAttribute("oraFine", parEndTime);
			req.getRequestDispatcher("detail-slot.jsp").forward(req, resp);
		} else {
			
			//Richiamo metodi per il savataggio
			
			resp.sendRedirect("list-slot");
		}
		
	}
}
