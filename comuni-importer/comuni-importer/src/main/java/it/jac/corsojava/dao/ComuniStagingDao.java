package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.entity.ComuneStagingEntity;

public class ComuniStagingDao extends BaseDao {

	private static Logger log = LogManager.getLogger(ComuniStagingDao.class);

	public boolean loadData(ComuneStagingEntity entity) {

		boolean result = false;

		StringBuilder sb = new StringBuilder();

		sb.append(" INSERT INTO comune_staging (");
		sb.append(" COD_REGIONE, COD_UNITA_TERR, COD_PROV, PROGRESSIVO_COMUNE, COD_COMUNE,");
		sb.append(" DEN_ITA_EXT, DEN_ITA, COD_RIP_GEO, DEN_RIP_GEO, DEN_REGIONE, DEN_UNITA_TERR, ");
		sb.append(" TIPOLOGIA_UNITA_TERR, FLAG_CAPOLUOGO, SIGLA_AUTO) ");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		try (Connection conn = openConnection()) {

			PreparedStatement pstm = conn.prepareStatement(sb.toString());
			int i = 1;
			pstm.setString(i++, entity.getCodRegione());
			pstm.setString(i++, entity.getCodUnitTerr());
			pstm.setString(i++, entity.getCodProv());
			pstm.setString(i++, entity.getProgressivoComune());
			pstm.setString(i++, entity.getCodComune());
			pstm.setString(i++, entity.getDenItaExt());
			pstm.setString(i++, entity.getDenIta());
			pstm.setString(i++, entity.getCodRipGeo());
			pstm.setString(i++, entity.getDenRipGeo());
			pstm.setString(i++, entity.getDenRegione());
			pstm.setString(i++, entity.getDenUnitaTerr());
			pstm.setString(i++, entity.getTipologiaUnitaTerr());
			pstm.setString(i++, entity.getFlagCapoluogo());
			pstm.setString(i++, entity.getSiglaAuto());

			result = pstm.executeUpdate() == 1; // Questo restituisce true o false

		} catch (SQLException e) {

			log.error("errore inserimento entity", e);
		}
		return result;
	}

}
