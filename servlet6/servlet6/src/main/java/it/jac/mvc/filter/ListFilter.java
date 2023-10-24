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

	private static Logger log = LogManager.getLogger(ListFilter.class);
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		log.debug("Intercettata richiesta");
		
		String requestURI = req.getRequestURI();
		
		log.debug("RequestURI {}", requestURI);
//		il filtro risponde a tutte le richieste, anche quelle di eventuali risorse statiche
//		immagini, fogli di stile, javascript etc etc
//		TODO implementare 
		if (requestURI.endsWith("/list")) {
			
			chain.doFilter(req, res);
			return;
		}
		
//		recupero l'informazione relativa all'utente loggato
		List<String> list = (List<String>)req.getSession().getAttribute("DB");
//		se non presente allora comunico al client di navigare verso la login
		if (list == null) {
			
			res.sendRedirect("/servlet6/list");
			return;
		}
		
//		tutto ok posso far proseguire la catena dei filtri
		chain.doFilter(req, res);
	}
}
