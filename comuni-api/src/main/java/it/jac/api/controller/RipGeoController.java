package it.jac.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.api.dao.RipGeoDao;
import it.jac.api.dto.RipGeo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/rip-geo")
public class RipGeoController {

	private static Logger log = LogManager.getLogger(RipGeoController.class);
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RipGeo> test() {
		
		log.info("Chiamato get regioni");
		
		List<RipGeo> list = new ArrayList<>();
		
		RipGeoDao dao = new RipGeoDao();
		list = dao.get();
		
		return list; 
	}
	
}
