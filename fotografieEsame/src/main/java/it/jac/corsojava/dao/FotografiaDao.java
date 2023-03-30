package it.jac.corsojava.dao;

import static it.jac.corsojava.dao.BaseDao.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import it.jac.corsojava.entity.Fotografia;
import it.jac.corsojava.entity.Proprieta;
import it.jac.corsojava.util.TransactionUtil;

public class FotografiaDao extends BaseDao{
	
	private static final String INSERT_FOTOGRAFIA_SQL = 
			"INSERT INTO fotografie "
			+ "(nome, formato, dimensione, utente_creazione, data_creazione)"
			+ " VALUES (?, ?, ?, ?, ?)";
	private static final String SELECT_FOTOGRAFIA_BY_ID = 
			"SELECT * FROM fotografie WHERE id = ?";
	private static final String SELECT_FOTOGRAFIA_BY_NOME = 
			"SELECT * FROM fotografie WHERE nome LIKE '%?%'";
	private static final String DELETE_FOTOGRAFIA_SQL = 
			"DELETE FROM fotografie WHERE id = ?";
	
	private ProprietaDao proprietaDao = new ProprietaDao();
	
	public void save(Fotografia entity) {
		
		//Salvo la fotografia
		Connection connection = TransactionUtil.getCurrentConnection();
		try { 
			
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_FOTOGRAFIA_SQL, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, entity.getNome());
			preparedStatement.setString(i++, entity.getFormato());
			preparedStatement.setDouble(i++, entity.getDimensione());
			preparedStatement.setString(i++, entity.getUtenteCreazione());
			preparedStatement.setObject(i++, entity.getDataCreazione());

			preparedStatement.executeUpdate(); 
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				entity.setId(keys.getInt(1));
			}
			
		} catch (SQLException e) {
			//Passo al chiamante l'eccezione altrimenti non viene ascoltata e il processo va avanti
			throw new RuntimeException("Error saving entity", e);
		}
	}
	
	//Questa funzione trova solo una fotografia
	public Fotografia findById(int id) {

		Fotografia result = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOTOGRAFIA_BY_ID)) {
		
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String nome = rs.getString("nome");
				String formato = rs.getString("formato");
				double dimensione = rs.getDouble("dimensione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				
				//Prendo la fotografia in se
				result = new Fotografia(id, nome, formato, dimensione, utenteCreazione, dataCreazione);
				//Riempio la fotografia con le varie proprietà
				result.getProprieta().addAll(this.proprietaDao.findById(id));
				
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return result;
	}
	
	//Questa funzione trova tutte le fotografie che hanno un nome che contiene la stringa passata;
	//ES. nome = anniversario --> trovo la foto: anniversario2023, anniversario2022, anniversarioPaolo...
	public ArrayList<Fotografia> findByNome(String nome) {
		
		ArrayList<Fotografia> resultList = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOTOGRAFIA_BY_NOME)) {
		
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String nomeCompleto = rs.getString("nome");
				String formato = rs.getString("formato");
				double dimensione = rs.getDouble("dimensione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				
				Fotografia entity = new Fotografia(id, nomeCompleto, formato, dimensione, utenteCreazione, dataCreazione);	
				entity.getProprieta().addAll(this.proprietaDao.findById(id));
				
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
			connection.prepareStatement(DELETE_FOTOGRAFIA_SQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}
	
}
