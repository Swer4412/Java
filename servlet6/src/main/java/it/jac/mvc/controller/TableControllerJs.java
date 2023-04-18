package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tablejs")
public class TableControllerJs extends HttpServlet {

	private static Logger log = LogManager.getLogger(LoginController.class);
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("ricevuta richiesta doGet");
		
		//Creo la lista
		List<User> list = new ArrayList<>();
		
		//Riempio la lista
		//Utilizzo dati demo
		for(int i=0; i < 25; i++) {
			list.add(new User(i, "nome" + i, "cognome" + i));
		}
		
		//Metto la lista nella request
		request.setAttribute("data", list);
		
		request.getRequestDispatcher("table.jsp").forward(request, response);
	}
}
