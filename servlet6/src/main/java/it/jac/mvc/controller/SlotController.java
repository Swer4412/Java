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

@WebServlet("/list-slot")
public class SlotController extends HttpServlet {

	private static Logger log = LogManager.getLogger(CalendarioController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Creo oggetto lista
		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		
		//Se la lista è vuota, assegno la lista alla sessione
		if (list == null) {
			list = new ArrayList<Slot>(); //creo una nuova lista vuota
			req.getSession().setAttribute("session.list.slot", list); //la assegno alla sessione
		}
		
		req.setAttribute("list", list);
		
		//Visualizzo contenuto sessione
		req.getRequestDispatcher("list-slot.jsp").forward(req,resp);
	}

}
