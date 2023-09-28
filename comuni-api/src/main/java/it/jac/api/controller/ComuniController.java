package it.jac.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.api.dao.ComuneDao;
import it.jac.api.dto.Comune;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/comuni")
public class ComuniController {

	private static Logger log = LogManager.getLogger(ComuniController.class);
	
	@GET
	@Path("/get")
	//Produces intende che ritorna un BODY di tipo json
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comune> test() {
		
		log.info("Chiamato get comuni");	
		
		List<Comune> list = new ArrayList<>();
		
		ComuneDao dao = new ComuneDao();
		list = dao.get();
		
		return list;  
	}
	
}
