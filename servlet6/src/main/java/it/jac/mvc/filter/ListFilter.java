package it.jac.mvc.filter;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter("/*")
public class ListFilter extends HttpFilter {
	private final Logger log = LogManager.getLogger(ListFilter.class);

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		//Se l'url finisce con /list, allora continua la richiesta
		if (req.getRequestURI().endsWith("/list")) {
			chain.doFilter(req, resp);
			return;
		}
		
		//Se la lista è vuota, riporta a lista
		List<String> list = (List)req.getSession().getAttribute("DB");
		if (list == null) {
			resp.sendRedirect("list");
			return;
		}
		
		chain.doFilter(req, resp);
	}
}