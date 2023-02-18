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

import it.jac.corsojava.entity.Prodotto;
import it.jac.corsojava.entity.StatoProdotto;

public class MagazzinoDao {

	private static Logger log = LogManager.getLogger(MagazzinoDao.class);

	public Connection openConnection() {

		Connection result = null;

		try { // Serve try perchè le 2 funzioni potrebbero dare problemi

			// La seguente funzione potrebbe non trovare la classe
			Class.forName("com.mysql.cj.jdbc.Driver"); // Carica il driver che serve per la connessione
			// Forza il caricamento in memoria della parte statica della classe

			String url = "jdbc:mysql://localhost:3306/magazzino"; // L'url lo cerchi su internet
			String username = "root"; // Utente creato dal database
			String password = "mysql";

			// La seguente funzione potrebbe non andare per vari problemi (driver non
			// aggiornati, database non esistenti, password sbagliata)
			result = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException | SQLException e) {

			log.error("Errore durante apertura connessione", e);

		}
		return result;
	}

	public void create(Prodotto prodotto) {

		Connection conn = null;
		PreparedStatement pstm = null; // Oggetto che rappresenta un'istruzione SQL precompilata

		// Utilizzo StringBuilder per creare una stringa sql in modo ordinato
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO magazzino.prodotto");
		sb.append("(cod, descrizione, prezzo, stato, data_creazione, utente_creazione)");
		sb.append("VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?)"); // I punti di domanda vengono visti da prepareStatement

		try {
			// Creo la connessione
			conn = openConnection();

			// Creo l'oggetto pstm che contiene la stringa sql con i campi "?" che riconosce
			pstm = conn.prepareStatement(sb.toString()); // Precompilare serve per velocizzare il processo di esecuzione
			// Facendo così dico al database che sto per mandare stringhe di questo tipo
			// Questo permette di fare in modo che il DBMS non debba interpretare l'intera
			// stringa ma solo quello che mando nei "?"

			// Utilizzo le funzioni setString, setDouble... per sostituire i "?" con i
			// valori
			pstm.setString(1, prodotto.getCod()); // 1 si riferisce al primo punto di domanda (?)
			pstm.setString(2, prodotto.getDescrizione());
			pstm.setDouble(3, prodotto.getPrezzo());
			pstm.setString(4, prodotto.getStato().toString());
			pstm.setTimestamp(5, java.sql.Timestamp.valueOf(prodotto.getDataCreazione()));
			pstm.setString(6, prodotto.getUtenteCreazione());

			int rowsUpdated = pstm.executeUpdate(); // Una volta eseguito l'update ritorna quante righe sono state
													// modificate

			log.debug("Aggiornate {} righe ", rowsUpdated);

		} catch (SQLException e) {
			log.error("Errore nell inserimento dei dati ", e);
		} finally {
			// Questa formattazione del codice è bruttissima ma per ora ce la facciamo
			// piacere
			try {
				conn.close();
			} catch (SQLException e) {
				// Non faccio nulla
			}

		}
	}

	public List<Prodotto> findAllProdotti() {

		List<Prodotto> result = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sb = new StringBuilder();

		sb.append(
				"SELECT id,cod,descrizione,prezzo,stato,data_creazione,utente_creazione,data_modifica,utente_modifica");
		sb.append(" FROM prodotto");

		try {

			conn = openConnection();
			pstm = conn.prepareStatement(sb.toString());

			ResultSet rs = pstm.executeQuery(); // ResultSet rappresenta un oggetto simile a una tabella insieme a un
												// cursore
			while (rs.next()) { // rs.next() muove il cursore nella riga succesiva (Il cursore all inizio si
								// trova sopra la prima riga)
				int id = rs.getInt("id"); // Perndo il valore della colonna id nella riga rs
				String cod = rs.getString("cod");
				String descrizione = rs.getString("descrizione");
				double prezzo = rs.getDouble("prezzo");
				String stato = rs.getString("stato");
				LocalDateTime dataCreazione = rs.getTimestamp("data_creazione").toLocalDateTime();
				String utenteCreazione = rs.getString("utente_creazione");
				LocalDateTime dataModifica = rs.getTimestamp("data_creazione").toLocalDateTime();
				String utenteModifica = rs.getString("utente_creazione");

				Prodotto prod = new Prodotto();

				prod.setId(id);
				prod.setCod(cod);
				prod.setDescrizione(descrizione);
				prod.setPrezzo(prezzo);
				prod.setStato(StatoProdotto.valueOf(stato));
				prod.setDataCreazione(dataCreazione);
				prod.setUtenteCreazione(utenteCreazione);
				prod.setDataModifica(dataModifica);
				prod.setUtenteModifica(utenteModifica);

				result.add(prod);

			}

		} catch (SQLException e) {
			log.error("Errore nella interrogazione del database ", e);
		} finally {
			// Questa formattazione del codice è bruttissima ma per ora ce la facciamo
			// piacere
			try {
				conn.close();
			} catch (SQLException e) {
				// Non faccio nulla
			}

		}

		return result;
	}
}
