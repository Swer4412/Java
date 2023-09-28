package it.jac.api.controller;

import java.time.LocalDate;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.api.dto.DataDto;
import it.jac.api.dto.TestResult;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/template")
public class ExampleRESTController {

	private static Logger log = LogManager.getLogger(ExampleRESTController.class);
	
	//Scrivo qui il medoto che attiva la richiesta
	@GET
	@Path("/test")
	//Jersey ha delle funzionalità interne che convertono in automatico l'oggetto java in json
	//Basta quindi questa stringa per dire che bisogna restistuire del json
	@Produces(MediaType.APPLICATION_JSON)
	
	public TestResult test() {

		log.info("API di test");
		
		TestResult result = new TestResult();
		result.setName("Andrea");
		result.setDate(LocalDate.of(2023, 4, 10));
		
		//result viene quindi convertito in json
		return result; 
	}
	
	//ENDPOINT POST
	@POST
	@Path("/create")
	//Che accetta json
	@Consumes(MediaType.APPLICATION_JSON)
	//E restituisce json
	@Produces(MediaType.APPLICATION_JSON)
	public DataDto create(DataDto data) {
		
		log.info("try to create a new Object");
		log.debug("DATA {}", data);
		
		//Data viene passato con username, data e numero gia pieni
		//Inseriamo poi l'id casuale che poi ritorniamo al client
		data.setId(new Random().nextInt(100));
		
		return data;
	}
	
}
