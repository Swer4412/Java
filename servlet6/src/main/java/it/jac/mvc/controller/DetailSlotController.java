package it.jac.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import it.jac.mvc.entity.Slot;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detail-slot")
public class DetailSlotController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Slot> list = (List<Slot>)req.getSession().getAttribute("session.list.slot");
		if (list == null) {

			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		String parId = req.getParameter("id");
		
		Slot slot = null;
		Optional<Slot> optSlot = 
			list.stream()
			.filter(s -> s.getId() == Integer.parseInt(parId))
			.findFirst();

		if (!optSlot.isPresent()) {
			
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
			
		} else {
			
			slot = optSlot.get();
		}
		
		req.setAttribute("slot", slot);
		req.setAttribute("action", "detail-slot");
		
		req.getRequestDispatcher("detail-slot.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("list-slot");
	}
}
