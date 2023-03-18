package it.jac.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
	//CTRL + T = mostra l'ereditarietà della classe
public class BaseDao { //Questa è stata creata per evitare di scrivere 5 volte l'apertura della connesisone
	//Questa è una parent class
	private static Logger log = LogManager.getLogger(BaseDao.class);

	private final String jdbcURL = "jdbc:mysql://localhost:3306/note_spese";
	private final String jdbcUsername = "root";
	private final String jdbcPassword = "mysql";

	public Connection getConnection() throws SQLException {

		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			log.warn("Unable to load Database DRIVER");
			throw new IllegalStateException("Unable to load Database DRIVER", e);
		}
		return connection;
	}

}
