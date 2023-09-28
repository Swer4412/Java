package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list") 
public class ListServlet extends HttpServlet { 
	
	private Logger log = LogManager.getLogger(ListServlet.class);

	public ListServlet() {}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Se c'è gia la prendo
		List<String> list = (List)req.getSession().getAttribute("DB");
		
		//Se non c'è la creo
		if (list == null) {
			list = new ArrayList<String>();
			list.add("valore riga1");
			list.add("valore riga2");
			req.getSession().setAttribute("DB", list);
		}
		
		req.getRequestDispatcher("list.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doPost");

		response.getWriter().write("<h1>Risposta del server alla POST</h1>");		
	}

}
