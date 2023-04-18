package it.jac.mvc.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static Logger log = LogManager.getLogger(LoginController.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String errorMessage = "";
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null || password == null) {
			
			errorMessage = "Inserire username e password";
			
		} else {
		
//			controllo le credenziali
//			se sono valide proseguo verso la HOME
			AuthService service = new AuthService();
			boolean login = service.login(username, password);
			if (!login) {
				errorMessage = "Credenziali errate. Ripetere l'operazione";
			} else {
//				importante, salvo in sessione l'attributo che poi verrà controllato dal FILTER
				req.getSession().setAttribute("loggedUser", username);
			}
		}		
		if (errorMessage.length() > 0) {
			req.setAttribute("errorMessage", errorMessage);
//			altrimenti ritorno sulla pagina con un messaggio di errore
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("/servlet6/home");
		}
	}
}
