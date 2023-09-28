package it.jac.mvc.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/primarequest") //@ vuol dire annotazione, serve per renderla visibile al servlet container
public class PrimaRequestController extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(PrimaRequestController.class);

	public PrimaRequestController() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String color = request.getParameter("color"); //Parameter = quello dopo ?color=cyan
		
		log.info("Ricevuta richiesta doGet");
		request.setAttribute("color", color);
		request.getRequestDispatcher("primarequest.jsp").forward(request, response);
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doPost");

		response.getWriter().write("<h1>Risposta del server alla POST</h1>");		
	}

}
