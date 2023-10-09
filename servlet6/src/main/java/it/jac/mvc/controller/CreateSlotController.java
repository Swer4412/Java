package it.jac.mvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.dto.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/slot-create")
public class CreateSlotController extends HttpServlet {

	private static Logger log = LogManager.getLogger(CalendarioController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("data-slot.jsp").forward(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//PRENDO PARAMETRI
		int id = 0;
		boolean update = false;
		
		try { //Se mi è stato passato devo aggiornare questo id
			id = Integer.parseInt(req.getParameter("id"));
			update = true;
		} catch (Exception e) { //Altrimenti lo genero io
			id = (int)Math.random() * 100000;
		}
		
		//converto String a LocalDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(req.getParameter("date"), formatter);
		
		LocalTime start = LocalTime.parse(req.getParameter("start"));
		LocalTime end = LocalTime.parse(req.getParameter("end"));
		
		//CREO OGGETTO SLOT
		Slot slot = new Slot(id, date, start, end, false);
		
		//Prendo la lista
		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		
		//Se l'id è stato passato come parametro
		if (update) {
			//Per ogni elemento dentro la lista 
			for (Slot s : list) {
				//Se l'id dentro la lista è uguale all'id passato
				if (s.getId() == id) {
					list.set(list.indexOf(s), slot);
				}
			}
		} else {
			list.add(slot);
		}
		
		resp.sendRedirect("list-slot");
		
		
	}

}
