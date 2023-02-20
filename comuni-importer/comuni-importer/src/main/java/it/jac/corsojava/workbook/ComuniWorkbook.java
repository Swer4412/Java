package it.jac.corsojava.workbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import it.jac.corsojava.entity.ComuneStagingEntity;

public class ComuniWorkbook {

	private List<ComuneStagingEntity> entityList = new ArrayList<>();

	// Load serve per caricare in memoria una lista di ComuneStagingEntity
	public void load(InputStream inputStream) throws IOException {

		// Creo un HSSFWorkbook che è una rappresentazione a oggetto di un progetto
		// excel
		// Passo come parametro inputstream che è l'inputstream del file excel
		try (HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) { // Workbook è il progetto

			// recupero il primo foglio disponibile nel file
			HSSFSheet sheet = workbook.getSheetAt(0); // Sheet è il foglio dei excel

			// mi sposto alla riga 2 perch� la prima ha l'intestazione
			// leggo tutte le righe fino a che non arrivo in fondo
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				// costruisco l'istanza che conterr� i valori della singola riga
				ComuneStagingEntity entity = new ComuneStagingEntity();

				HSSFRow row = sheet.getRow(i); // è la riga di excel

				// leggo le 14 colonne che mi interessano e inserisco i valori
				// all'interno dell'istanza che ho preparato

				// COD_REGIONE
				entity.setCodRegione(row.getCell(0).getStringCellValue());
				// COD_UNITA_TERR
				entity.setCodUnitTerr(row.getCell(1).getStringCellValue());
				// COD_PROV
				entity.setCodProv(row.getCell(2).getStringCellValue());
				// PROGRESSIVO_COMUNE
				entity.setProgressivoComune(row.getCell(3).getStringCellValue());
				// COD_COMUNE
				entity.setCodComune(row.getCell(4).getStringCellValue());
				// DEN_ITA_EXT
				entity.setDenItaExt(row.getCell(5).getStringCellValue());
				// DEN_ITA
				entity.setDenIta(row.getCell(6).getStringCellValue());
				// COD_RIP_GEO
				entity.setCodRipGeo(String.valueOf(Double.valueOf(row.getCell(8).getNumericCellValue()).intValue()));
				// DEN_RIP_GEO
				entity.setDenRipGeo(row.getCell(9).getStringCellValue());
				// DEN_REGIONE
				entity.setDenRegione(row.getCell(10).getStringCellValue());
				// DEN_UNITA_TERR
				entity.setDenUnitaTerr(row.getCell(11).getStringCellValue());
				// TIPOLOGIA_UNITA_TERR
				entity.setTipologiaUnitaTerr(
						String.valueOf(Double.valueOf(row.getCell(12).getNumericCellValue()).intValue()));
				// FLAG_CAPOLUOGO
				entity.setFlagCapoluogo(String.valueOf(Double.valueOf(row.getCell(13).getNumericCellValue())));
				// SIGLA_AUTO
				entity.setSiglaAuto(row.getCell(14).getStringCellValue());

				this.entityList.add(entity);
			}
		}
	}

	public List<ComuneStagingEntity> getEntityList() {
		// Utlizzo il metodo Collections.unmodifiableList per ritornare una vista della
		// lista che non è modificabile
		return Collections.unmodifiableList(this.entityList);
	}
}
