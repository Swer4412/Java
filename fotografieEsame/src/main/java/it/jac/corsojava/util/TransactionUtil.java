package it.jac.corsojava.util;

import java.sql.Connection;
import java.sql.SQLException;

import it.jac.corsojava.dao.BaseDao;

public class TransactionUtil {
	
	private static ThreadLocal<Connection> currentConnection = new ThreadLocal<>();
	//Creo un threadlocal di tipo connessione
	
	public static Connection getCurrentConnection() {
		return currentConnection.get();
	}
	
	
	public static Connection beginTransaction() {
		
		//Creo una connessione
		BaseDao dao = new BaseDao();
		
		Connection connection = null;
		
		try {
			
			connection = dao.getConnection();
			connection.setAutoCommit(false);
			//Passo la connessione al thread di tipo connection
			currentConnection.set(connection);
			
		} catch (SQLException e) {
			throw new RuntimeException("Unable to open connection");
		}
		
		//Ora per prendre il contentuto del thread devo scrivere currentConnection
		
		return connection;
		
	}
	
	public static void commit() {
		
		try {
			currentConnection.get().commit();
		} catch (SQLException e) {
			throw new RuntimeException("Unable to commit");
		}
	
	}
	
	public static void rollback() {
		
		try {
			currentConnection.get().rollback();
		} catch (SQLException e) {
			throw new RuntimeException("Unable to rollback");
		}
		
	}
	
	
	
}
