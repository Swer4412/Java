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

@WebServlet("/slot-create")
public class CreateSlotController extends HttpServlet {

	private static Logger log = LogManager.getLogger(CalendarioController.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("data-slot.jsp").forward(req,resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Prendo la lista
		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		
		System.out.println(list);
		
		
	}

}
