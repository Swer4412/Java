package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Entity;
import it.jac.corsojava.entity.NotaSpesa;
import it.jac.corsojava.entity.Societa;
import it.jac.corsojava.entity.StatoSpesa;
import it.jac.corsojava.entity.VoceSpesa;

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
		
		//Estraggo gli oggetti che mi servono da entity
		NotaSpesa ns = entity.getNota_spesa();
		ArrayList<VoceSpesa> voci = entity.getVoci_spesa();
		//Non estraggo dipendente e categoria perchè li ho gia inseriti in questi 2 oggetti
		
		//INSERIMENTO
		//Inserisco la nota spesa
		
		StringBuilder notaSpesaSb = new StringBuilder();
		notaSpesaSb.append("INSERT INTO note_spese.nota_spesa");
		notaSpesaSb.append(" (cod, mese_rif, importo_totale, stato, id_dipendente, utente_creazione, data_creazione)");
		notaSpesaSb.append(" VALUES");
		notaSpesaSb.append(" (?, ?, ?, ?, ?, ?, ?)");
		
		StringBuilder voceSpesaSb = new StringBuilder();
		notaSpesaSb.append("INSERT INTO note_spese.voce_spesa");
		notaSpesaSb.append(" (commento, importo, id_nota_spesa, id_categoria, utente_creazione, data_creazione)");
		notaSpesaSb.append(" VALUES");
		notaSpesaSb.append(" (?, ?, ?, ?, ?, ?)");
		
		try {

			conn = openConnection();

			pstm = conn.prepareStatement(notaSpesaSb.toString());

			pstm.setString(1, ns.getCodice());
			pstm.setString(1, ns.getMese_rif());
			pstm.setDouble(1, ns.getImporto_totale());
			pstm.setString(1, ns.getStato().toString());
			pstm.setInt(1, ns.getId_dipendente());
			pstm.setString(1, ns.getUtente_creazione());
			pstm.setTimestamp(1, java.sql.Timestamp.valueOf(ns.getData_creazione()));

			int rowsUpdated = pstm.executeUpdate();
			
			log.debug("Aggiornate {} righe in nota_spesa ", rowsUpdated);
			
			//Inserisco le voci spesa
			for (VoceSpesa vs : voci) {
				
				//Devo trovare l'id generato dal database in modo da linkare le voci spesa alla nota spesa
				int id_ns = read().get(read().size()-1).getNota_spesa().getId(); 

				pstm = conn.prepareStatement(voceSpesaSb.toString());

				pstm.setString(1, vs.getCommento());
				pstm.setDouble(1, vs.getImporto());
				pstm.setInt(1, id_ns);
				pstm.setInt(1, vs.getId_categoria());
				pstm.setString(1, vs.getUtente_creazione());
				pstm.setTimestamp(1, java.sql.Timestamp.valueOf(vs.getData_creazione()));
				
				rowsUpdated += pstm.executeUpdate();
				
			}
			log.debug("Aggiornate {} righe in voceSpesa ", rowsUpdated-1);
			

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

		ArrayList<Entity> entitys = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		
		//Creo i vari stringbuilder
		//Societa
		StringBuilder societaSb = new StringBuilder();
		societaSb.append(
				"SELECT id,cod,denominazione,utente_creazione,data_creazione,utente_modifica,data_modifica");
		societaSb.append(" FROM societa");
		
		//Dipendente
		StringBuilder dipendenteSb = new StringBuilder();
		dipendenteSb.append(
				"SELECT id,matricola,nome,cognome,id_societa,data_nascita,utente_creazione,data_creazione,utente_modifica,data_modifica");
		dipendenteSb.append(" FROM dipendente");
		
		//Nota Spesa
		StringBuilder notaSpesaSb = new StringBuilder();
		notaSpesaSb.append(
				"SELECT id,cod,mese_rif,importo_totale,stato,id_dipendente,utente_creazione,data_creazione,utente_modifica,data_modifica");
		notaSpesaSb.append(" FROM nota_spesa");
		
		//Voce Spesa
		StringBuilder voceSpesaSb = new StringBuilder();
		notaSpesaSb.append(
				"SELECT id,commento,importo,id_nota_spesa,id_categoria,utente_creazione,data_creazione,utente_modifica,data_modifica");
		notaSpesaSb.append(" FROM voce_spesa");
		
		//Categoria Spesa
		StringBuilder categoriaSb = new StringBuilder();
		societaSb.append(
				"SELECT id,cod,descrizione,utente_creazione,data_creazione,utente_modifica,data_modifica");
		societaSb.append(" FROM societa");
		
		try {

			conn = openConnection();
			
			//Prendo le societa
			pstm = conn.prepareStatement(societaSb.toString());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				
				Societa so = new Societa();
				
				int id = rs.getInt("id");

				so.setId(id);
				

				entitys.add(so);

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
