package it.jac.mvc.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add") 
public class AddServlet extends HttpServlet { 

	public AddServlet() {
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		req.getRequestDispatcher("add.jsp").forward(req, resp);
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String item = req.getParameter("item");
		//Item potrebbe non essere assegnato dall'utente
		if (item != null) {
			List<String> list = (List)req.getSession().getAttribute("DB");
			//La lista è nulla se accedo direttamente 
			if (list != null) {
				list.add(item);
				req.getSession().setAttribute("DB", list);
			}
		}
		
		resp.sendRedirect("list"); //Scrivo solo list perchè il path è relativo
	}

}
