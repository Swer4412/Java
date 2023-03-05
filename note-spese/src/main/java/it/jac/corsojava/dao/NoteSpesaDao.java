package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.CategoriaSpesa;
import it.jac.corsojava.entity.Dipendente;
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

			pstm.setString(1, ns.getCod());
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

		ArrayList<Entity> entityList = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		
		//Dichiaro liste per il controllo dell'univocità
		ArrayList<Integer> soList = new ArrayList<>();
		ArrayList<Integer> dipList = new ArrayList<>();
		ArrayList<Integer> nsList = new ArrayList<>();
		ArrayList<Integer> vsList = new ArrayList<>();
		ArrayList<Integer> catList = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(
				"SELECT *");
		sb.append(" FROM societa so inner join dipendente dip on so.id=dip.id_societa");
		sb.append(" inner join nota_spesa ns on dip.id=ns.id_dipendente");
		sb.append(" inner join voce_spesa vs on ns.id=vs.id_nota_spesa");
		sb.append(" inner join categoria_spesa cat on cat.id=vs.id_categoria");
		
		
		try {

			conn = openConnection();
			
			pstm = conn.prepareStatement(sb.toString());

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				
				Entity entity = new Entity();
				
				int soId = rs.getInt("so.id");
				
				if (!soList.contains(soId)) { //Controllo se l'id è gia stato registrato
					soList.add(soId); //Aggiungo alla lista degli id registrati
					
					Societa so = new Societa();
					so.setId(soId);
					so.setCod(rs.getString("so.cod"));
					so.setDenominazione(rs.getString("so.denominazione"));
					so.setUtente_creazione(rs.getString("so.utente_creazione"));
					so.setData_creazione(rs.getTimestamp("so.data_creazione").toLocalDateTime());
					so.setUtente_modifica(rs.getString("so.utente_modifica"));
					try {
						so.setData_modifica(rs.getTimestamp("so.data_modifica").toLocalDateTime());
						} catch (Exception e) {so.setData_modifica(null); }
					entity.setSocieta(so);
				}
				
				int dipId = rs.getInt("dip.id");
				
				if (!dipList.contains(dipId)) { 
					dipList.add(dipId);
					
					Dipendente dip = new Dipendente();
					dip.setId(dipId);
					dip.setMatricola(rs.getString("dip.utente_creazione"));
					dip.setNome(rs.getString("dip.utente_creazione"));
					dip.setCognome(rs.getString("dip.utente_creazione"));
					dip.setId_societa(rs.getInt("dip.id_societa"));
					dip.setData_nascita(rs.getTimestamp("dip.data_nascita").toLocalDateTime());
					dip.setUtente_creazione(rs.getString("dip.utente_creazione"));
					dip.setData_creazione(rs.getTimestamp("dip.data_creazione").toLocalDateTime());
					dip.setUtente_modifica(rs.getString("dip.utente_modifica"));
					try {
						dip.setData_modifica(rs.getTimestamp("dip.data_modifica").toLocalDateTime());
						} catch (Exception e) {dip.setData_modifica(null); }
					entity.setDipendente(dip);
				}
				
				int nsId = rs.getInt("ns.id");
				
				if (!nsList.contains(nsId)) { 
					nsList.add(nsId);
					NotaSpesa ns = new NotaSpesa();
					ns.setId(nsId);
					ns.setCod(rs.getString("ns.cod"));
					ns.setMese_rif(rs.getString("ns.mese_rif"));
					ns.setImporto_totale(rs.getDouble("ns.importo_totale"));
					ns.setStato(StatoSpesa.valueOf(rs.getString("stato")));
					ns.setId_dipendente(rs.getInt("ns.id_dipendente"));
					ns.setUtente_creazione(rs.getString("ns.utente_creazione"));
					ns.setData_creazione(rs.getTimestamp("ns.data_creazione").toLocalDateTime());
					ns.setUtente_modifica(rs.getString("ns.utente_modifica"));
					try {
						ns.setData_modifica(rs.getTimestamp("ns.data_modifica").toLocalDateTime());
						} catch (Exception e) {ns.setData_modifica(null); }
					entity.setNota_spesa(ns);
				}
				
				//Inserisco le vociSpesa
				ArrayList<VoceSpesa> voci = new ArrayList<>();
				
				StringBuilder voceSpesaSb = new StringBuilder();
				voceSpesaSb.append(
						"SELECT id,commento,importo,id_nota_spesa,id_categoria,utente_creazione,data_creazione,utente_modifica,data_modifica");
				voceSpesaSb.append(" FROM voce_spesa");
				
				Connection con = null;
				con = openConnection();
				PreparedStatement pstmm = null;
				pstmm = con.prepareStatement(voceSpesaSb.toString());

				ResultSet rsVs = pstmm.executeQuery();
				
				//Ciclo tutte le voci spesa
				while (rsVs.next()) {
					if (rs.getInt("ns.id")==rsVs.getInt("id_nota_spesa")) { //Se l'id di questa notaSpesa è uguale a id_nota_spesa
						
						int vsId = rs.getInt("vs.id");
						
						if (!vsList.contains(vsId)) { 
							vsList.add(vsId);
							VoceSpesa vs = new VoceSpesa();
						
							vs.setId(vsId);
							vs.setCommento(rsVs.getString("commento"));
							vs.setImporto(rsVs.getDouble("importo"));
							vs.setId_nota_spesa(rsVs.getInt("id_nota_spesa"));
							vs.setId_categoria(rsVs.getInt("id_categoria"));
							vs.setUtente_creazione(rsVs.getString("utente_creazione"));
							vs.setData_creazione(rsVs.getTimestamp("data_creazione").toLocalDateTime());
							vs.setUtente_modifica(rsVs.getString("utente_modifica"));
							try {
								vs.setData_modifica(rs.getTimestamp("vs.data_modifica").toLocalDateTime());
								} catch (Exception e) {vs.setData_modifica(null); }
							
							voci.add(vs);
							}
						}
					}
				try {
					con.close();
				} catch (SQLException e) {
					// Non faccio nulla
				}
				entity.setVoci_spesa(voci);
				
				int catId = rs.getInt("cat.id");
				
				if (!catList.contains(catId)) { 
					catList.add(catId);
				
					CategoriaSpesa cat = new CategoriaSpesa();
					
					cat.setId(catId);
					cat.setCod(rs.getString("cat.cod"));
					cat.setUtente_creazione(rs.getString("cat.descrizione"));
					cat.setUtente_creazione(rs.getString("cat.utente_creazione"));
					cat.setData_creazione(rs.getTimestamp("cat.data_creazione").toLocalDateTime());
					cat.setUtente_modifica(rs.getString("cat.utente_modifica"));
					try {
						cat.setData_modifica(rs.getTimestamp("cat.data_modifica").toLocalDateTime());
						} catch (Exception e) {cat.setData_modifica(null); }
					entity.setCategoria_spesa(cat);
				}
				//Aggiungo l'entità alla lista
				entityList.add(entity);
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

		return entityList;
	}
	
	/*private ArrayList<Entity> removeDuplicates(ArrayList<Entity> entityList) {
		for (Entity ent : entityList) {
			HashSet<Societa> soHs = new HashSet<>();
			ent.getSocieta().forEach(value -> { soHs.add(value); });
		}
		
	}*/
	
	

	public void update(Entity entity) {

		NotaSpesa ns = entity.getNota_spesa();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE nota_spesa");
		sb.append(" SET stato=?, utente_modifica=?, data_modifica=?");
		sb.append(" WHERE id=?");

		try {

			conn = openConnection();
			pstm = conn.prepareStatement(sb.toString());

			pstm.setString(1, ns.getStato().toString());
			pstm.setString(2, ns.getUtente_modifica());
			pstm.setTimestamp(3, java.sql.Timestamp.valueOf(ns.getData_modifica()));
			pstm.setInt(4, ns.getId());

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

	public void delete(int id) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM nota_spesa");
		sb.append(" WHERE id=?");

		try {

			conn = openConnection();
			pstm = conn.prepareStatement(sb.toString());

			pstm.setInt(1, id);

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
	
}
