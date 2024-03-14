package it.jac.springboot.apicomuni.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.jac.springboot.apicomuni.entity.Comune;
import it.jac.springboot.apicomuni.servizi.ComuniService;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/comuni")
public class ComuniController {
	
	private static Logger log = LogManager.getLogger(ComuniController.class);
	
	// @Autowired Si potrebbe usare autowired ma è meglio utilizzare il costruttore
	private ComuniService service;
	
	public ComuniController(ComuniService service) {
		
		this.service = service;
	}
	
	@GetMapping //Questo specifica che questa funzione si occupa della get
	public List<Comune> findAll() {
		log.info("richiamato metodo get findAll");
		
		List<Comune> list = service.findAll();
		
		log.info("ritornato qualcosa");
		
		return list;
	}
}
