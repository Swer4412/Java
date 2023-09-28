package it.jac.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.jac.api.dto.Comune;

public class ComuneDao extends BaseDao{
	
	public List<Comune> get() {

		List<Comune> result = new ArrayList<>();

		PreparedStatement pstm = null;
		Connection conn = null;
		String query = "SELECT * FROM comune AS co INNER JOIN unita_terr AS ut on co.COD_UNITA_TERR = ut.COD_UNITA_TERR";
		
		try {

			conn = getConnection();
			
			pstm = conn.prepareStatement(query);

			ResultSet rs = pstm.executeQuery(); 
			
			while (rs.next()) { 
				
				Comune comune = new Comune();
				
				comune.setCodComune(rs.getString("co.COD_COMUNE"));
				comune.setUnitaTerr(rs.getString("ut.DEN_UNITA_TERR"));
				comune.setProgressivoComune(rs.getString("co.PROGRESSIVO_COMUNE"));
				comune.setDenIta(rs.getString("co.DEN_ITA"));
				comune.setDenItaExt(rs.getString("co.DEN_ITA_EXT"));
				comune.setFlagCapoluogo(rs.getString("co.FLAG_CAPOLUOGO"));
				
				result.add(comune);

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

		return result;
	}
	
	
}
