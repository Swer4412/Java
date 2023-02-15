package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MagazzinoDao {
	
	public Connection openConnection() {
		Class.forName("com.mysql.cj.jdbc.Driver"); //Logicamente carica il driver
		//Class.forName() Forza il caricamento in memoria della parte statica della classe
		
		String url ="jdbc:mysql://localhost:3306/magazzino"; // L'url lo cerchi su internet
		String username="root";
		String password="mysql";
		
		DriverManager.getConnection(url, username, password);
		
		return ;
	}
}
