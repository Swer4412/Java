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
import it.jac.db.entity.VoceSpesa;
import it.jac.db.util.TransactionUtil;

public class VoceSpesaDao extends BaseDao {

private static Logger log = LogManager.getLogger(CategoriaDao.class);
	
	private static final String INSERT_VOCE_SPESA_SQL = 
			"INSERT INTO voce_spesa "
			+ "(commento, importo, id_nota_spesa, id_categoria, utente_creazione, data_creazione)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_VOCE_SPESA_BY_ID = 
			"SELECT * FROM voce_spesa WHERE id = ?";
	private static final String SELECT_VOCE_SPESA_BY_NOTA = 
			"SELECT * FROM voce_spesa WHERE id_nota_spesa = ?";
	private static final String UPDATE_VOCE_SPESA_SQL = 
			"UPDATE voce_spesa SET commento = ?, importo = ?, id_nota_spesa = ?, id_categoria = ?, utente_modifica = ?, "
			+ "data_modifica = ? WHERE id = ?";
	private static final String DELETE_VOCE_SPESA_SQL = 
			"DELETE FROM voce_spesa WHERE id = ?";

	private CategoriaDao categoriaDao = new CategoriaDao();
	
	public void save(VoceSpesa entity) {
		
		Connection connection = TransactionUtil.getCurrentConnection();
		
		try {
			PreparedStatement preparedStatement = 
					connection.prepareStatement(INSERT_VOCE_SPESA_SQL, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			preparedStatement.setString(i++, entity.getCommento());
			preparedStatement.setDouble(i++, entity.getImporto());
			preparedStatement.setInt(i++, entity.getNotaSpesa().getId());
			preparedStatement.setInt(i++, entity.getCategoria().getId());
			preparedStatement.setString(i++, entity.getUtenteCreazione());
			preparedStatement.setObject(i++, entity.getDataCreazione());

			preparedStatement.executeUpdate();
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if (keys.next()) {
				entity.setId(keys.getInt(1));
			}
			
		} catch (SQLException e) {
			
			throw new RuntimeException("Error saving entity");
		}
	}

	public VoceSpesa findById(int id) {

		VoceSpesa result = null;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOCE_SPESA_BY_ID)) {
		
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String commento = rs.getString("commento");
				double importo = rs.getDouble("importo");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);
				
				result = new VoceSpesa(id, commento, importo, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				result.setNotaSpesa(null);
				result.setCategoria(this.categoriaDao.findById(rs.getInt("id_categoria")));
				
			}
		} catch (SQLException e) {
			log.error("Error reading entity", e);
		}
		return result;
	}

	public List<VoceSpesa> findByNota(NotaSpesa notaSpesa) {
		
		List<VoceSpesa> resultList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOCE_SPESA_BY_NOTA)) {
			
			preparedStatement.setInt(1, notaSpesa.getId());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				String commento = rs.getString("commento");
				double importo = rs.getDouble("importo");
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataCreazione = rs.getObject("data_creazione", LocalDateTime.class);
				String utenteModifica = rs.getString("utente_modifica");
				LocalDateTime dataModifica = rs.getObject("data_modifica", LocalDateTime.class);

				VoceSpesa entity = new VoceSpesa(id, commento, importo, utenteCreazione, dataCreazione, utenteModifica,
						dataModifica);
				
				resultList.add(entity);
				
				entity.setNotaSpesa(notaSpesa);
				entity.setCategoria(this.categoriaDao.findById(rs.getInt("id_categoria")));
				
			}
		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return resultList;
	}

	public boolean update(VoceSpesa voceSpesa) {
		
		boolean rowUpdated = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VOCE_SPESA_SQL)) {
		
			int i = 1;
			preparedStatement.setString(i++, voceSpesa.getCommento());
			preparedStatement.setDouble(i++, voceSpesa.getImporto());
			preparedStatement.setInt(i++, voceSpesa.getNotaSpesa().getId());
			preparedStatement.setInt(i++, voceSpesa.getCategoria().getId());
			preparedStatement.setString(i++, voceSpesa.getUtenteModifica());
			preparedStatement.setObject(i++, voceSpesa.getDataModifica());
			preparedStatement.setInt(i++, voceSpesa.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			
			log.error("Error updating entities", e);
		}
		return rowUpdated;
	}

	public boolean delete(int id) {
		
		boolean rowDeleted = false;
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VOCE_SPESA_SQL)) {
		
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			log.error("Error reading entities", e);
		}
		return rowDeleted;
	}
}