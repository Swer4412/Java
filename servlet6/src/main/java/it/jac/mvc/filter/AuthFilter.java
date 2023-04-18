package it.jac.mvc.filter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter("/*")
public class AuthFilter extends HttpFilter {
	private final Logger log = LogManager.getLogger(AuthFilter.class);

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		log.debug("Intercetta richiesta");
		
		//Qua prendo la uri
		String requestURI = req.getRequestURI();
		log.debug("RequestURI {}", requestURI);
		
		//Qua guardo se finisce con /login es. servlet6/login
		if (requestURI.endsWith("/login")) {
			
			//Questo vol dire passa avanti nell'esecuzione dei filtri
			chain.doFilter(req, res);
			//Qua non faccio redirect perchè l'url richiesto è gia /login
			//Una volta finiti i filtri, viene eseguita la servlet in base a quale url è chiamato
			return;
		}
		
		//Guardo se nella sessione è presente l'username
		String username = (String) req.getSession().getAttribute("loggedUser");
		
		if (username == null) {
			res.sendRedirect("login");
			return;
		}
		//Senza dofilter, non si sblocca più, boh
		chain.doFilter(req, res);
	}
}