package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.jac.mvc.entity.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/list-slot")
public class ListSlotController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		if (list == null) {
			
			list = new ArrayList<>();
			req.getSession().setAttribute("session.list.slot", list);
		}
		
		req.getRequestDispatcher("list-slot.jsp").forward(req, resp);
	}
}
