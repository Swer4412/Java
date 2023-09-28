package it.jac.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.api.dao.BaseDao;
import it.jac.api.dao.UnitaTerrDao;
import it.jac.api.dto.UnitaTerr;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/unita-terr")
public class UnitaTerrController extends BaseDao{

	private static Logger log = LogManager.getLogger(UnitaTerrController.class);
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UnitaTerr> test() {
		
		log.info("Chiamato get Unità territoriali");
		
		List<UnitaTerr> list = new ArrayList<>();
		
		UnitaTerrDao dao = new UnitaTerrDao();
		list = dao.get();
		
		return list; 
	}
	
}
