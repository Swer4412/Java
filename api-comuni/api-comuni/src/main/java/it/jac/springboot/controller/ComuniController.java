package it.jac.springboot.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.jac.springboot.entity.Comune;
import it.jac.springboot.entity.SortComuniColumns;
import it.jac.springboot.service.ComuniService;

@RestController
@RequestMapping("/api/v1/comuni")
public class ComuniController {

	private static Logger log = LogManager.getLogger(ComuniController.class);
	private ComuniService service;
	
	public ComuniController(ComuniService service) {
		
		this.service = service;
	}
	
	@GetMapping
	public List<Comune> findAll(
			@RequestParam(name = "p", defaultValue = "1") int page,
			@RequestParam(name = "s", defaultValue = "10") int size,
			@RequestParam(name = "o", defaultValue = "denIta") SortComuniColumns order) {
		
		log.info("richiamato metodo GET findAll");
		
//		ottengo le informazioni dello user autenticato
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		authentication.getAuthorities().stream().forEach(a -> {
			log.debug("Authority user {} - {}", name, a);
		});
		
		List<Comune> list = service.findAll(size, page, order.name());
		
		log.info("restituisco {} elementi", list.size());
		
		return list;
	}
	
}
