package it.jac.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.jac.api.dto.Regione;

public class RegioneDao extends BaseDao{
	
	public List<Regione> get() {

		List<Regione> result = new ArrayList<>();

		PreparedStatement pstm = null;
		Connection conn = null;
		String query = "SELECT * FROM regione AS re INNER JOIN rip_geo AS rg on re.COD_RIP_GEO = rg.COD_RIP_GEO";
		
		try {

			conn = getConnection();
			
			pstm = conn.prepareStatement(query);

			ResultSet rs = pstm.executeQuery(); 
			
			while (rs.next()) { 
				
				Regione regione = new Regione();
				
				regione.setCodRegione(rs.getString("re.COD_REGIONE"));
				regione.setDenRegione(rs.getString("re.DEN_REGIONE"));
				regione.setRipGeo(rs.getString("rg.DEN_RIP_GEO"));
				
				result.add(regione);

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
