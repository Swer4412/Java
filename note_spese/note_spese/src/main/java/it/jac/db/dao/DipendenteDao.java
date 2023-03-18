package it.jac.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.db.entity.Dipendente;

public class DipendenteDao extends BaseDao {

private static Logger log = LogManager.getLogger(CategoriaDao.class);
	
	private static final String INSERT_DIPENDENTE_SQL = 
			"INSERT INTO dipendente "
			+ "(matricola, nome, cognome, data_nascita, utente_creazione, data_creazione) "
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_DIPENDENTE_BY_ID = 
			"SELECT * FROM dipendente WHERE id = ?";
	private static final String SELECT_DIPENDENTE_BY_MATRICOLA = 
			"SELECT * FROM dipendente WHERE matricola = ?";
	private static final String SELECT_ALL_DIPENDENTE = 
			"SELECT * FROM dipendente";
	private static final String UPDATE_DIPENDENTE_SQL = 
			"UPDATE dipendente SET matricola = ?, nome = ?, cognome = ?, data_nascita = ?, id_societa = ?, utente_modifica = ?, "
			+ "data_modifica = ? WHERE id = ?";
	private static final String DELETE_DIPENDENTE_SQL = 
			"DELETE FROM dipendente WHERE id = ?";

	private SocietaDao societaDao = new SocietaDao();
	
	public void save(Dipendente dipendente) {
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(INSERT_DIPENDENTE_SQL, Statement.RETURN_GENERATED_KEYS)) {

			int i = 1;
			preparedStatement.setString(i++, dipendente.getMatricola());
			preparedStatement.setString(i++, dipendente.getNome());
			preparedStatement.setString(i++, dipendente.getCognome());
			preparedStatement.setObject(i++, dipendente.getDataNascita());
			preparedStatement.setString(i++, dipendente.getUtenteCreazione());
			preparedStatement.setObject(i++, dipendente.getDataCreazione());

			preparedStatement.executeUpdate();
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				dipendente.setId(keys.getInt(1));
			}
			
		} catch (SQLException e) {
			
			log.error("Error saving entity", e);
		}
	}

	public Dipendente findById(int id) {

		Dipendente dipendente = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIPENDENTE_BY_ID)) {
		
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String matricola = rs.getString("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				LocalDate dataNascita = rs.getObject("data_nascita", LocalDate.class);
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);
				
				dipendente = new Dipendente(id, matricola, nome, cognome, dataNascita, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				dipendente.setSocieta(this.societaDao.findById(rs.getInt("id_societa")));
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return dipendente;
	}

	public Dipendente findByMatricola(String matricola) {

		Dipendente dipendente = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DIPENDENTE_BY_MATRICOLA)) {
		
			preparedStatement.setString(1, matricola);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				LocalDate dataNascita = rs.getObject("data_nascita", LocalDate.class);
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);
				
				dipendente = new Dipendente(id, matricola, nome, cognome, dataNascita, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				dipendente.setSocieta(this.societaDao.findById(rs.getInt("id_societa")));
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return dipendente;
	}

	public List<Dipendente> findAll() {
		
		List<Dipendente> dipendenteList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIPENDENTE)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String matricola = rs.getString("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				LocalDate dataNascita = rs.getObject("data_nascita", LocalDate.class);
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				Dipendente dipendente = new Dipendente(id, matricola, nome, cognome, dataNascita, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				dipendenteList.add(dipendente);

				dipendente.setSocieta(this.societaDao.findById(rs.getInt("id_societa")));
			}
		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return dipendenteList;
	}

	public boolean update(Dipendente dipendente) {
		
		boolean rowUpdated = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DIPENDENTE_SQL)) {
		
			int i = 1;
			preparedStatement.setString(i++, dipendente.getMatricola());
			preparedStatement.setString(i++, dipendente.getNome());
			preparedStatement.setString(i++, dipendente.getCognome());
			preparedStatement.setObject(i++, dipendente.getDataNascita());
			preparedStatement.setInt(i++, dipendente.getSocieta().getId());
			preparedStatement.setString(i++, dipendente.getUtenteModifica());
			preparedStatement.setObject(i++, dipendente.getDataModifica());
			preparedStatement.setInt(i++, dipendente.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			
			log.error("Error updating entities", e);
		}
		return rowUpdated;
	}

	public boolean delete(int id) {
		
		boolean rowDeleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DIPENDENTE_SQL)) {
		
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}
}