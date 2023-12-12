package it.jac.mvc.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthService {

	private static Logger log = LogManager.getLogger(AuthService.class);
	
	private static Map<String, String> cacheUsers = new HashMap<>();
	
	static {
		cacheUsers.put("user1", "123");
		cacheUsers.put("user2", "qwe");
		cacheUsers.put("user3", "poi");
		cacheUsers.put("admin", "admin");
	}
	
	public boolean login(String username, String password) {
		
		boolean result = false;
		
//		cerco la entry con la username
		String entry = cacheUsers.get(username);
		if (entry != null && entry.equals(password)) {
			result = true;
		} else {
			
//			username/password errate
			log.warn("Username/Password errate");
		}
		
		return result;
	}
}
