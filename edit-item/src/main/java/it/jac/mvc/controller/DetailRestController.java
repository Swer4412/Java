package it.jac.mvc.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import it.jac.mvc.entity.Item;
import it.jac.mvc.service.ItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/item/*") //* Serve per inserire qulunque stringa
public class DetailRestController extends HttpServlet {
	
	private static Logger log = LogManager.getLogger(DetailRestController.class);
	
	private ItemService service = new ItemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Estraggo uri string
		String uri = req.getRequestURI();
		log.info("uri = {}", uri);
		
		//Estraggo id da uri
		int parItemId = 0;
		try {
			parItemId = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
			log.info("trovato id = {}", parItemId);
		} catch (Exception e) {
			log.error(e);
		}
		
		//Recupero item dall'id
		Item item = this.service.findItemById(parItemId);
		if (item == null) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} 
		log.info("Trovato item: {}", item.getId());
		
		//Gestisco impostazioni risposta
		resp.setContentType("application/json");
		
		//Risolvo cors
		resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
		 
		//Converto item in testo
		JsonMapper jsonMapper = new JsonMapper();
		jsonMapper.registerModule(new JavaTimeModule());
		jsonMapper.writeValue(resp.getWriter(), item);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String parId = req.getParameter("id");
		
		if (parId == null) {
			System.out.println("Id non passato");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//Provo a vedere se il valore passato come parametro è valido
		int id = 0;
		
		try {
			id = Integer.parseInt(parId);
		} catch (NumberFormatException e) {
			System.out.println("Id non valido");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		//Controllo se nel database esiste o no l'item
		Item item = (Item) req.getSession().getAttribute("item");
		if (item == null) {
			System.out.println("Item non trovato");
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		//Confronta tra item della sessione e item del database 
//		Item itemDb = this.service.findItemById(id);
//		if (!item.equals(itemDb)) {
//			System.out.println("Item nella sessione e item nel database sono diversi");
//			System.out.println(item.getCode() + itemDb.getCode());
//			resp.sendRedirect("detail?id=" + id);
//			return;
//		}
		
//		Leggere parametro code, description, longDescription
		String code = req.getParameter("code");
		String description = req.getParameter("description");
		String longDescription = req.getParameter("longDescription");
		
//		Aggiornare item con attributi reguperati da parametro
		item.setCode(code);
		item.setDescription(description);
		item.setLongDescription(longDescription);
		
//		richiamare metoo dle servizio per aggiornare record su db
		this.service.updateItem(item);
		
		resp.sendRedirect("detail?id=" + id);
	}
}
