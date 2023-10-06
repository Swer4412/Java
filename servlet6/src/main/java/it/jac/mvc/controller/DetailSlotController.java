package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.dto.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/slot-detail")
public class DetailSlotController extends HttpServlet {

	private static Logger log = LogManager.getLogger(CalendarioController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Prendo la lista
		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		
		//Prendo l'id passato come parametro
		int id = Integer.parseInt(req.getParameter("id"));
		
		Slot slot = null;
		
		//Trovo slot con tale id
		for (Slot s : list) {
			if (s.getId() == id) {
				slot = s;
			}
		}
		
		req.setAttribute("slot", slot);
		
		req.getRequestDispatcher("data-slot.jsp").forward(req,resp);
	}

}
