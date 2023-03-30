package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import it.jac.corsojava.entity.Proprieta;
import it.jac.corsojava.util.TransactionUtil;

public class ProprietaDao extends BaseDao {
	
	private static final String INSERT_PROPRIETA_SQL = 
			"INSERT INTO proprieta "
			+ "(chiave, valore, id_foto, utente_creazione, data_creazione)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_PROPRIETA_BY_ID = 
			"SELECT * FROM proprieta WHERE id = ?";
	private static final String DELETE_PROPRIETA_SQL = 
			"DELETE FROM proprieta WHERE id = ?";
	
	public void save(Proprieta entity) {
		
		
		Connection connection = TransactionUtil.getCurrentConnection();
		try { 
			
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_PROPRIETA_SQL, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, entity.getChiave());
			preparedStatement.setString(i++, entity.getValore());
			preparedStatement.setInt(i++, entity.getFotografia().getId());
			preparedStatement.setString(i++, entity.getUtenteCreazione());
			preparedStatement.setObject(i++, entity.getDataCreazione());

			preparedStatement.executeUpdate(); //Adesso va in ram, non nel database
			
			//Generated keys prende tutte le cose generate nel database una volta eseguito executeUpdate()
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				entity.setId(keys.getInt(1));
			}
			
		} catch (SQLException e) {
			//Passo al chiamante l'eccezione altrimenti non viene ascoltata e il processo va avanti
			throw new RuntimeException("Error saving entity", e);
		}
	}
	
	//Trova tutte le proprietà della foto in base all'id di tale foto
	public ArrayList<Proprieta> findById(int id) { 

		ArrayList<Proprieta> resultList = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROPRIETA_BY_ID)) {
		
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String chiave = rs.getString("chiave");
				String valore = rs.getString("valore");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				
				Proprieta entity = new Proprieta(id, chiave, valore, utenteCreazione, dataCreazione);
				//Non serve la fotografia perchè questa funzione ritorna proprietà che finiscono nella fotografia
				entity.setFotografia(null);
				
				resultList.add(entity);
				
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return resultList;
	}
	public boolean delete(int id) {
		
		boolean rowDeleted = false;
		
		Connection connection = TransactionUtil.getCurrentConnection();
		try { 
			
			PreparedStatement preparedStatement = 
			connection.prepareStatement(DELETE_PROPRIETA_SQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}
}
