package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<String> list = (List)req.getSession().getAttribute("DB");
		if (list == null) {
			
			list = new ArrayList<String>();
			list.add("valore riga1");
			list.add("valore riga2");
			req.getSession().setAttribute("DB", list);
		}
		
		req.getRequestDispatcher("list.jsp").forward(req, resp);
	}
}
