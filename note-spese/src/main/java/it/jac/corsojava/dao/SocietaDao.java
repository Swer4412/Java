package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Societa;

public class SocietaDao {
	
	private static Logger log = LogManager.getLogger(NoteSpesaDao.class);

	public Connection openConnection() {

		Connection result = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/note_spese";
			String username = "root";
			String password = "mysql";

			result = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {

			log.error("Errore durante apertura connessione", e);

		}
		return result;
	}
	
	public void create(Societa societa) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO societa");
		sb.append(" (cod, denominazione, utente_creazione, data_creazione)");
		sb.append(" VALUES (?, ?, ?, ?,)");
		
		try {
		
			pstm = conn.prepareStatement(sb.toString());
			
			pstm.setString(1, societa.getCod());
			pstm.setString(2, societa.getDenominazione());
			pstm.setString(3, societa.getUtente_creazione());
			pstm.setTimestamp(4, societa.getCod());
			pstm.setString(5, societa.getCod());
			pstm.setTimestamp(6, societa.getCod());
		
		} catch (SQLException e) {
			log.error("Errore nell inserimento dei dati ", e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				//Non faccio niente
			}
		}
		
	}
	
	public Societa read() {
		
		
		
		return;
	}
	
	public void update(Societa societa) {
		
	}
	
	public void delete(int id) {
		
	}
}
