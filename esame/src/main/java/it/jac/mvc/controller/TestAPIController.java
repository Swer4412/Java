package it.jac.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.jac.mvc.dto.DaysDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class TestAPIController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger(TestAPIController.class);

	public TestAPIController() {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doGet");
		
		//Creo la lista da passare al chiamante
		List<DaysDto> list = new ArrayList<>();
		
		//Riempio la lista di oggetti in modo manuale
		DaysDto day1 = new DaysDto();
		day1.setDay("mercoledi");
		day1.setDayOfMonth("19");
		day1.setDayOfWeek("3");
		day1.setDayOfYear("150");
		list.add(day1);
		
		DaysDto day2 = new DaysDto();
		day2.setDay("giovedi");
		day2.setDayOfMonth("20");
		day2.setDayOfWeek("4");
		day2.setDayOfYear("151");
		list.add(day2);
		
		//Importo il traduttore di oggetti java in json
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(list);
		
		//Imposto il content type di tipo json in modo che il browser riesca a capire cos'è che gli mando
		response.setContentType("application/json");
		
		//GetWriter prende un oggetto che riesce a mandare data al client
		response.getWriter().append(json);
		
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("Ricevuta richiesta doPost");

		response.getWriter().write("<h1>Risposta del server alla POST</h1>");		
	}

}
