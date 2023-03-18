package it.jac.db.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.db.entity.Categoria;

public class CategoriaDao extends BaseDao { //Extends vuol dire che categoria dao implementa le funzioni della classe baseDao
	
	private static Logger log = LogManager.getLogger(CategoriaDao.class);
	//Queste sono le funzioni crud ma read ha diverse versoni
	//E' prevista la scrittura del sql in cima alla classe come delle costanti
	//Questo rende più semplice la lettura e la modifica
	private static final String INSERT_CATEGORIA_SQL = 
			"INSERT INTO categoria_spesa "
			+ "(cod, descrizione, utente_creazione, data_creazione) "
			+ " VALUES (?, ?, ?, ?)";
	private static final String SELECT_CATEGORIA_BY_ID = 
			"SELECT * FROM categoria_spesa WHERE id = ?";
	private static final String SELECT_CATEGORIA_BY_COD = 
			"SELECT * FROM categoria_spesa WHERE cod = ?";
	private static final String SELECT_ALL_CATEGORIA = 
			"SELECT * FROM categoria_spesa";
	private static final String UPDATE_CATEGORIA_SQL = 
			"UPDATE categoria_spesa SET cod = ?, descrizione = ?, utente_modifica = ?, "
			+ "data_modifica = ? WHERE id = ?";
	private static final String DELETE_CATEGORIA_SQL = 
			"DELETE FROM categoria_spesa WHERE id = ?";

	public void save(Categoria categoria) { //Viene passato l'oggetto, non tutti i singoli campi
		//Non si chiama create ma save, create lo chiamiano in service
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(INSERT_CATEGORIA_SQL, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, categoria.getCod());
			preparedStatement.setString(2, categoria.getDescrizione());
			preparedStatement.setString(3, categoria.getUtenteCreazione());
			preparedStatement.setObject(4, categoria.getDataCreazione());

			preparedStatement.executeUpdate();
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				categoria.setId(keys.getInt(1));
			}
			
		} catch (SQLException e) {
			
			log.error("Error saving entity", e);
		}
	}

	public Categoria findById(int id) {

		Categoria categoria = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_BY_ID)) {
		
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String cod = rs.getString("cod");
				String descrizione = rs.getString("descrizione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);
				
				categoria = new Categoria(id, cod, descrizione, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return categoria;
	}
	
	public Categoria findByCod(String cod) {

		Categoria categoria = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_BY_COD)) {
		
			preparedStatement.setString(1, cod);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String descrizione = rs.getString("descrizione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);
				
				categoria = new Categoria(id, cod, descrizione, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return categoria;
	
	}

	public List<Categoria> findAll() {
		
		List<Categoria> categoriaList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIA)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cod = rs.getString("cod");
				String descrizione = rs.getString("descrizione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				categoriaList.add(new Categoria(id, cod, descrizione, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica));
			}
		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return categoriaList;
	}

	public boolean update(Categoria categoria) {
		
		boolean rowUpdated = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORIA_SQL)) {
		
			preparedStatement.setString(1, categoria.getCod());
			preparedStatement.setString(2, categoria.getDescrizione());
			preparedStatement.setString(3, categoria.getUtenteModifica());
			preparedStatement.setObject(4, categoria.getDataModifica());
			preparedStatement.setInt(5, categoria.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			
			log.error("Error updating entities", e);
		}
		return rowUpdated;
	}

	public boolean delete(int id) {
		
		boolean rowDeleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORIA_SQL)) {
		
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}

}
