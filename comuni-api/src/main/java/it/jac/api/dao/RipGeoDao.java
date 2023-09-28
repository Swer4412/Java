package it.jac.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.jac.api.dto.RipGeo;

public class RipGeoDao extends BaseDao{
	
	public List<RipGeo> get() {

		List<RipGeo> result = new ArrayList<>();

		PreparedStatement pstm = null;
		Connection conn = null;
		String query = "SELECT * FROM rip_geo AS rg";
		
		try {

			conn = getConnection();
			
			pstm = conn.prepareStatement(query);

			ResultSet rs = pstm.executeQuery(); 
			
			while (rs.next()) { 
				
				RipGeo ripGeo = new RipGeo();
				
				ripGeo.setCodRipGeo(rs.getString("rg.COD_RIP_GEO"));
				ripGeo.setDenRipGeo(rs.getString("rg.DEN_RIP_GEO"));
				
				result.add(ripGeo);

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
