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
	
	public void create(Societa societa) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO societa");
		sb.append(" (cod, denominazione, utente_creazione, data_creazione)");
		sb.append(" VALUES (?, ?, ?, ?)");
		
		try {
		
			pstm = conn.prepareStatement(sb.toString());
			
			pstm.setString(1, societa.getCod());
			pstm.setString(2, societa.getDenominazione());
			pstm.setString(3, societa.getUtente_creazione());
			pstm.setTimestamp(4, java.sql.Timestamp.valueOf(societa.getData_creazione()));
			
			int rows = pstm.executeUpdate();
			
			log.debug("Aggiornate {} righe in società", rows);
		
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
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT *");
		sb.append(" FROM societa");
		
		try {
			
		} catch (SQLException e) {
			
		}
		
		
		
		return;
	}
	
	public void update(Societa societa) {
		
	}
	
	public void delete(int id) {
		
	}
}
