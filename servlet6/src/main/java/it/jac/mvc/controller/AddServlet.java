package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("add.jsp").forward(req, resp);		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String item = req.getParameter("item");
		if (item != null) {
			
			List<String> list = (List<String>)req.getSession().getAttribute("DB");
			if (list != null) {
				
				list.add(item);
				req.getSession().setAttribute("DB", list);
			}
		}
		
		resp.sendRedirect("list");
	}
}
