package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Entity;
import it.jac.corsojava.entity.StatoSpesa;

public class NoteSpesaDao {

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

	public boolean create(Entity entity) {
		
		boolean success = false;
		
		Connection conn = null;
		PreparedStatement pstm = null;

		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO magazzino.prodotto");
		sb.append("(cod, descrizione, prezzo, stato, data_creazione, utente_creazione)");
		sb.append("VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?)");

		try {

			conn = openConnection();

			pstm = conn.prepareStatement(sb.toString());

			pstm.setString(1, entity.get());

			int rowsUpdated = pstm.executeUpdate();

			log.debug("Aggiornate {} righe ", rowsUpdated);

		} catch (SQLException e) {
			log.error("Errore nell inserimento dei dati ", e);
		} finally {

			try {
				conn.close();
			} catch (SQLException e) {
				// Non faccio nulla
			}

		}
		
		return success;
	}

	public ArrayList<Entity> read() {

		ArrayList<Entity> entity = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sb = new StringBuilder();

		sb.append(
				"SELECT id,cod,descrizione,prezzo,stato,data_creazione,utente_creazione,data_modifica,utente_modifica");
		sb.append(" FROM prodotto");

		try {

			conn = openConnection();
			pstm = conn.prepareStatement(sb.toString());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String cod = rs.getString("cod");
				String descrizione = rs.getString("descrizione");
				double prezzo = rs.getDouble("prezzo");
				String stato = rs.getString("stato");
				LocalDateTime dataCreazione = rs.getTimestamp("data_creazione").toLocalDateTime();
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataModifica = rs.getTimestamp("data_creazione").toLocalDateTime();
				String utenteModifica = rs.getString("utente_creazione");

				Entity prod = new Entity();

				prod.setId(id);
				prod.setCod(cod);
				prod.setDescrizione(descrizione);
				prod.setPrezzo(prezzo);
				prod.setStato(StatoSpesa.valueOf(stato));
				prod.setDataCreazione(dataCreazione);
				prod.setUtenteCreazione(utenteCreazione);
				prod.setDataModifica(dataModifica);
				prod.setUtenteModifica(utenteModifica);

				result.add(prod);

			}

		} catch (SQLException e) {
			log.error("Errore nella interrogazione del database ", e);
		} finally {

			try {
				conn.close();
			} catch (SQLException e) {
				// Non faccio nulla
			}

		}

		return entity;
	}

	public void update(Entity prod) {

		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sb = new StringBuilder();

		sb.append("UPDATE prodotto");
		sb.append(" SET stato=?");
		sb.append(" WHERE id=?");

		try {

			conn = openConnection();
			pstm = conn.prepareStatement(sb.toString());

			pstm.setString(1, prod.getStato().toString());
			pstm.setInt(2, prod.getId());

			int rowsUpdated = pstm.executeUpdate(); // Una volta eseguito l'update ritorna quante righe sono state
			// modificate

			log.debug("Aggiornate {} righe ", rowsUpdated);

		} catch (SQLException e) {
			log.error("Errore nella interrogazione del database ", e);
		} finally {

			try {
				conn.close();
			} catch (SQLException e) {
				// Non faccio nulla
			}

		}
	}

	public void delete(Entity entity) {

	}
}
