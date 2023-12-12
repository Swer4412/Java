package it.jac.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp; 

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.mvc.entity.Slot;

public class SlotDao {
	
	private static Logger log = LogManager.getLogger(SlotDao.class);
	
	public static SlotDao instance = new SlotDao(); //Creo una sola istanza
	
	private SlotDao() {} //Non permetto l'instanzializzazione esterna
	
	public static SlotDao getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws SQLException {
		Connection result = null;
		String username = "root";
		String password = "mysql";
		String jdbcurl = "jsbc:mysql://localhost:3306/calendario";
		
		result = DriverManager.getConnection(jdbcurl, username, password);
		
		log.debug("Aperta nuova connessione {}", result);
		
		return result;
	}
	
	public void save(Slot entity) {

		//Ottentere una connessione
		try {
			Connection conn = getConnection();
			
			//Preparare sql per inserimento
			StringBuilder sb = new StringBuilder();
			sb.append("insert into slot (data_inizio, data_fine, id_risorsa, data_creazione, utente_creazione)");
			sb.append(" values (?, ?, ?, ?, ?)");
			
			//Lancia comando verso base dati
			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getStartDateTime()));
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getEndDateTime()));
			pstm.setInt(i++, entity.getRisorsa().getId());
			pstm.setTimestamp(i++, Timestamp.valueOf(entity.getDataCreazione()));
			pstm.setString(i++, entity.getUtenteCreazione());
			
			pstm.executeUpdate();
			
		} catch (SQLException e) { 
			
			//Non serve mettere un try catch nel blocco del chimante
			throw new RuntimeException("Errore nel salvataggio Slot", e);
		}
		
		//Gestire eventuali errori
	}
	
}
