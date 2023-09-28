package it.jac.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.jac.api.dto.UnitaTerr;

public class UnitaTerrDao extends BaseDao{
	
	public List<UnitaTerr> get() {

		List<UnitaTerr> result = new ArrayList<>();

		PreparedStatement pstm = null;
		Connection conn = null;
		String query = "SELECT * FROM unita_terr AS ut INNER JOIN regione AS re on ut.COD_REGIONE = re.COD_REGIONE";
		
		try {

			conn = getConnection();
			
			pstm = conn.prepareStatement(query);

			ResultSet rs = pstm.executeQuery(); 
			
			while (rs.next()) { 
				
				UnitaTerr unitaTerr = new UnitaTerr();
				
				unitaTerr.setCodUnitaTerr(rs.getString("ut.COD_UNITA_TERR"));
				unitaTerr.setCodProv(rs.getString("ut.COD_PROV"));
				unitaTerr.setDenUnitaTerr(rs.getString("ut.DEN_UNITA_TERR"));
				unitaTerr.setTipologiaUnitaTerr(rs.getString("ut.TIPOLOGIA_UNITA_TERR"));
				unitaTerr.setRegione(rs.getString("re.DEN_REGIONE"));
				unitaTerr.setSiglaAuto(rs.getString("ut.SIGLA_AUTO"));
				
				result.add(unitaTerr);

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
