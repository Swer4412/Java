package it.jac.corsojava;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.jac.corsojava.dao.ComuniStagingDao;
import it.jac.corsojava.entity.ComuneStagingEntity;
import it.jac.corsojava.workbook.ComuniWorkbook;

public class MainImportExcel {

	private static Logger log = LogManager.getLogger(MainImportExcel.class);

	public static void main(String[] args) {

		log.info("Importer started");

		try {

			ComuniWorkbook workbook = new ComuniWorkbook(); // Creo l'oggetto per poterne utilizzare i metodi
			workbook.load(new FileInputStream("c:/temp/istat/comuni.xls")); // Passo come input stream un nuovo
																			// inputstream da un file

			List<ComuneStagingEntity> list = workbook.getEntityList();
			log.info("caricati {} oggetti", list.size());

			ComuniStagingDao dao = new ComuniStagingDao();

			int i = 0;
			for (ComuneStagingEntity entity : list) {
				dao.loadData(entity);

				if (i % 1000 == 0) {
					log.info("scritti " + i + " elementi");
				}
				i++;
			}

		} catch (Exception e) {

			log.error("Error reading excel file", e);
		}

		log.info("Importer finished");

	}
}
