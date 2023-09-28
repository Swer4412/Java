package it.jac.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.api.dao.RegioneDao;
import it.jac.api.dto.Regione;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/regioni")
public class RegioniController {

	private static Logger log = LogManager.getLogger(RegioniController.class);
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Regione> test() {
		
		log.info("Chiamato get regioni");
		
		List<Regione> list = new ArrayList<>();
		
		RegioneDao dao = new RegioneDao();
		list = dao.get();
		
		return list; 
	}
	
}
