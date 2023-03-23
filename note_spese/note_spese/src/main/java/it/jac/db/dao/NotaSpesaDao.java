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

import it.jac.db.entity.NotaSpesa;
import it.jac.db.util.TransactionUtil;

public class NotaSpesaDao extends BaseDao {

private static Logger log = LogManager.getLogger(CategoriaDao.class);
	
	private static final String INSERT_NOTA_SPESA_SQL = 
			"INSERT INTO nota_spesa "
			+ "(cod, mese_rif, importo_totale, stato, id_dipendente, utente_creazione, data_creazione)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_NOTA_SPESA_BY_ID = 
			"SELECT * FROM nota_spesa WHERE id = ?";
	private static final String SELECT_NOTA_SPESA_BY_MATRICOLA = 
			"SELECT n.* FROM nota_spesa n inner join dipendente d on (n.id_dipendente = d.id) WHERE n.matricola = ?";
	private static final String SELECT_NOTA_SPESA_BY_STATO = 
			"SELECT * FROM nota_spesa WHERE stato = ?";
	private static final String SELECT_ALL_NOTA_SPESA = 
			"SELECT * FROM nota_spesa";
	private static final String UPDATE_NOTA_SPESA_SQL = 
			"UPDATE nota_spesa SET cod = ?, mese_rif = ?, importo_totale = ?, stato = ?, id_dipendente = ?, utente_modifica = ?, "
			+ "data_modifica = ? WHERE id = ?";
	private static final String DELETE_NOTA_SPESA_SQL = 
			"DELETE FROM nota_spesa WHERE id = ?";

	private DipendenteDao dipendenteDao = new DipendenteDao();
	private VoceSpesaDao voceSpesaDao = new VoceSpesaDao();
	
	public void save(NotaSpesa entity) {
		
		
		Connection connection = TransactionUtil.getCurrentConnection();
		try { 
			
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_NOTA_SPESA_SQL, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, entity.getCod());
			preparedStatement.setString(i++, entity.getMeseRif());
			preparedStatement.setDouble(i++, entity.getImportoTotale());
			preparedStatement.setString(i++, entity.getStato());
			preparedStatement.setInt(i++, entity.getDipendente().getId());
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

	public NotaSpesa findById(int id) {

		NotaSpesa result = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTA_SPESA_BY_ID)) {
		
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String cod = rs.getString("cod");
				String meseRif = rs.getString("mese_rif");
				double importoTotale = rs.getDouble("importo_totale");
				String stato = rs.getString("stato");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);
				
				result = new NotaSpesa(id, cod, meseRif, importoTotale, stato, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				result.setDipendente(this.dipendenteDao.findById(rs.getInt("id_dipendente")));
				result.getVociSpesa().addAll(this.voceSpesaDao.findByNota(result));

				
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return result;
	}

	public List<NotaSpesa> findByStato(String stato) {

		List<NotaSpesa> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTA_SPESA_BY_STATO)) {
		
			preparedStatement.setString(1, stato);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cod = rs.getString("cod");
				String meseRif = rs.getString("mese_rif");
				double importoTotale = rs.getDouble("importo_totale");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				NotaSpesa notaSpesa = new NotaSpesa(id, cod, meseRif, importoTotale, stato, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				resultList.add(notaSpesa);

				notaSpesa.setDipendente(this.dipendenteDao.findById(rs.getInt("id_dipendente")));
				notaSpesa.getVociSpesa().addAll(this.voceSpesaDao.findByNota(notaSpesa));
				
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return resultList;
	}

	public List<NotaSpesa> findByMatricola(String matricola) {

		List<NotaSpesa> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTA_SPESA_BY_MATRICOLA)) {
		
			preparedStatement.setString(1, matricola);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cod = rs.getString("cod");
				String meseRif = rs.getString("mese_rif");
				String stato = rs.getString("stato");
				double importoTotale = rs.getDouble("importo_totale");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				NotaSpesa notaSpesa = new NotaSpesa(id, cod, meseRif, importoTotale, stato, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				resultList.add(notaSpesa);

				notaSpesa.setDipendente(this.dipendenteDao.findById(rs.getInt("id_dipendente")));
				notaSpesa.getVociSpesa().addAll(this.voceSpesaDao.findByNota(notaSpesa));
				
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return resultList;
	}

	public List<NotaSpesa> findAll() {
		
		List<NotaSpesa> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTA_SPESA)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String cod = rs.getString("cod");
				String meseRif = rs.getString("mese_rif");
				double importoTotale = rs.getDouble("importo_totale");
				String stato = rs.getString("stato");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				NotaSpesa notaSpesa = new NotaSpesa(id, cod, meseRif, importoTotale, stato, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				resultList.add(notaSpesa);

				notaSpesa.setDipendente(this.dipendenteDao.findById(rs.getInt("id_dipendente")));
				notaSpesa.getVociSpesa().addAll(this.voceSpesaDao.findByNota(notaSpesa));
				
			}
		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return resultList;
	}

	public boolean update(NotaSpesa notaSpesa) {
		
		boolean rowUpdated = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NOTA_SPESA_SQL)) {
		
			int i = 1;
			preparedStatement.setString(i++, notaSpesa.getCod());
			preparedStatement.setString(i++, notaSpesa.getMeseRif());
			preparedStatement.setDouble(i++, notaSpesa.getImportoTotale());
			preparedStatement.setString(i++, notaSpesa.getStato());
			preparedStatement.setInt(i++, notaSpesa.getDipendente().getId());
			preparedStatement.setString(i++, notaSpesa.getUtenteModifica());
			preparedStatement.setObject(i++, notaSpesa.getDataModifica());
			preparedStatement.setInt(i++, notaSpesa.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			
			log.error("Error updating entities", e);
		}
		return rowUpdated;
	}

	public boolean delete(int id) {
		
		boolean rowDeleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTA_SPESA_SQL)) {
		
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}
}