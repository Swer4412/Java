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

import it.jac.db.entity.Societa;

public class SocietaDao extends BaseDao {

	private static Logger log = LogManager.getLogger(SocietaDao.class);

	private static final String INSERT_SOCIETA_SQL = "INSERT INTO societa "
			+ "(cod, denominazione, utente_creazione, data_creazione) "
			+ " VALUES (?, ?, ?, ?)";
	private static final String SELECT_SOCIETA_BY_ID = "SELECT * FROM societa WHERE id = ?";
	private static final String SELECT_ALL_SOCIETA = "SELECT * FROM societa";
	private static final String UPDATE_SOCIETA_SQL = "UPDATE societa SET cod = ?, denominazione = ?, utente_modifica = ?, "
			+ "data_modifica = ? WHERE id = ?";
	private static final String DELETE_SOCIETA_SQL = "DELETE FROM societa WHERE id = ?";

	public void save(Societa societa) {

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = 
						connection.prepareStatement(INSERT_SOCIETA_SQL, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, societa.getCod());
			preparedStatement.setString(2, societa.getDenominazione());
			preparedStatement.setString(3, societa.getUtenteCreazione());
			preparedStatement.setObject(4, societa.getDataCreazione());

			preparedStatement.executeUpdate();

			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				societa.setId(keys.getInt(1));
			}

		} catch (SQLException e) {

			log.error("Error saving entity", e);
		}
	}

	public Societa findById(int id) {

		Societa societa = null;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SOCIETA_BY_ID)) {

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String cod = rs.getString("cod");
				String denominazione = rs.getString("denominazione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				societa = new Societa(id, cod, denominazione, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return societa;
	}

	public List<Societa> findAll() {

		List<Societa> societaList = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SOCIETA)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cod = rs.getString("cod");
				String denominazione = rs.getString("denominazione");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				societaList.add(new Societa(id, cod, denominazione, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica));
			}
		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return societaList;
	}

	public boolean update(Societa societa) {

		boolean rowUpdated = false;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SOCIETA_SQL)) {

			preparedStatement.setString(1, societa.getCod());
			preparedStatement.setString(2, societa.getDenominazione());
			preparedStatement.setString(3, societa.getUtenteModifica());
			preparedStatement.setObject(4, societa.getDataModifica());
			preparedStatement.setInt(5, societa.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {

			log.error("Error updating entities", e);
		}
		return rowUpdated;
	}

	public boolean delete(int id) {

		boolean rowDeleted = false;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOCIETA_SQL)) {

			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}
}
