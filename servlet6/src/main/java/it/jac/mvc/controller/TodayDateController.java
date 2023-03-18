package it.jac.mvc.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/date")
public class TodayDateController extends HttpServlet{
		
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(TodayDateController.class);

	public TodayDateController() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doGet");
		
		response.getWriter().write(LocalDateTime.now().toString());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doPost");

		response.getWriter().write(LocalDateTime.now().toString());		
	}

}

