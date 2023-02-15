package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.Prodotto;

public class MagazzinoDao {
	
	private static Logger log = LogManager.getLogger(MagazzinoDao.class);
	
	public Connection openConnection() {
		
		Connection result = null;
		
		try { //Serve try perchè le 2 funzioni potrebbero dare problemi
		
			//La seguente funzione potrebbe non trovare la classe
			Class.forName("com.mysql.cj.jdbc.Driver"); //Carica il driver che serve per la connessione
			//Forza il caricamento in memoria della parte statica della classe
			
			String url ="jdbc:mysql://localhost:3306/magazzino"; // L'url lo cerchi su internet
			String username="root"; // Utente creato dal database
			String password="mysql";
			
			//La seguente funzione potrebbe non andare per vari problemi (driver non aggiornati, database non esistenti, password sbagliata)
			result = DriverManager.getConnection(url, username, password);
		
		} catch (ClassNotFoundException | SQLException e) {
			
			log.error("Errore durante apertura connessione", e);
		
		}
		return result;
	}
	
	public void create(Prodotto prodotto) {
		
		Connection conn = null;
		PreparedStatement pstm = null; // Oggetto che rappresenta un'istruzione SQL precompilata
		
		//Utilizzo StringBuilder per creare una stringa sql in modo ordinato
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO magazzino.prodotto");
		sb.append("(cod, descrizione, prezzo, stato, data_creazione, utente_creazione)");
		sb.append("VALUES");
		sb.append(" (?, ?, ?, ?, ?, ?)"); //I punti di domanda vengono visti da prepareStatement
		
		try {
		//Creo la connessione
		conn = openConnection();
		
		//Creo l'oggetto pstm che contiene la stringa sql con i campi "?" che riconosce
		pstm = conn.prepareStatement(sb.toString()); //Precompilare serve per velocizzare il processo di esecuzione
		//Facendo così dico al database che sto per mandare stringhe di questo tipo
		//Questo permette di fare in modo che il DBMS non debba interpretare l'intera stringa ma solo quello che mando nei "?"
		
		//Utilizzo le funzioni setString, setDouble... per sostituire i "?" con i valori
		pstm.setString(1, prodotto.getCod()); //1 si riferisce al primo punto di domanda (?)
		pstm.setString(2, prodotto.getDescrizione());
		pstm.setDouble(3, prodotto.getPrezzo());
		pstm.setString(4, prodotto.getStato().toString());
		pstm.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now()));
		pstm.setString(6, "java");
		
		int rowUpdated = pstm.executeUpdate(); //Una volta eseguito l'update ritorna quante righe sono state modificate
		
		
		log.debug("Aggiornate {} righe ", rowUpdated);
		
		} catch (SQLException e){
			log.error("Errore nell inserimento dei dati ", e);
		} finally {
			//Questa formattazione del codice è bruttissima ma per ora ce la facciamo piacere
			try {
				conn.close();
			} catch (SQLException e) {
				//Non faccio nulla
			}

		}
		
		
		
		
		
		
		
	}
}
