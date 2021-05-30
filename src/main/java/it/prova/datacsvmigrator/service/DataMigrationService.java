package it.prova.datacsvmigrator.service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.prova.datacsvmigrator.connection.MyConnection;
import it.prova.datacsvmigrator.dao.Constants;
import it.prova.datacsvmigrator.dao.assicurato.AssicuratoDaoImpl;
import it.prova.datacsvmigrator.dao.notprocessed.NotProcessedDaoImpl;
import it.prova.datacsvmigrator.flow.file.CSVService;
import it.prova.datacsvmigrator.model.Assicurato;
import it.prova.datacsvmigrator.model.CSVTransientObj;
import it.prova.datacsvmigrator.model.NotProcessed;

public class DataMigrationService {

	private AssicuratoDaoImpl assicuratoDaoInstance;
	private NotProcessedDaoImpl notProcessedDaoInstance;
	private CSVService csvServiceInstance;
	
	public void inserimentoAssicurati(File fileCSVInput) throws Exception {

		List<CSVTransientObj> csvTransientObjs = new ArrayList<>();

		try {

			csvTransientObjs = csvServiceInstance.csvToJava(fileCSVInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		List<Assicurato> assicurati = new ArrayList<>();
		List<NotProcessed> nonProcessati = new ArrayList<>();

		for (CSVTransientObj objItem : csvTransientObjs) {
			if (validate(objItem)) {

				Assicurato assicurato = new Assicurato();
				assicurato.setNome(objItem.getNome());
				assicurato.setCognome(objItem.getCognome());
				assicurato.setDataNascita(objItem.getDataNascita());
				assicurato.setCodiceFiscale(objItem.getCodiceFiscale());
				assicurato.setNumeroSinistri(objItem.getNumeroSinistri() == null ? 0 : objItem.getNumeroSinistri());
				assicurati.add(assicurato);

			} else {

				NotProcessed notProcessed = new NotProcessed();
				notProcessed.setCodiceFiscale(objItem.getCodiceFiscale());
				notProcessed.setOldId(objItem.getId().toString());
				nonProcessati.add(notProcessed);

			}
		}

		try (Connection connectionNew = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			assicuratoDaoInstance.setConnection(connectionNew);
			notProcessedDaoInstance.setConnection(connectionNew);
			notProcessedDaoInstance.insert(nonProcessati);
			assicuratoDaoInstance.insert(assicurati);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public boolean validate(CSVTransientObj objInstance) {

		if (StringUtils.isBlank(objInstance.getNome())) {
			return false;
		}
		if (StringUtils.isBlank(objInstance.getCognome())) {
			return false;
		}
		if (StringUtils.isBlank(objInstance.getCodiceFiscale()) || objInstance.getCodiceFiscale().length() != 16) {
			return false;
		}
		if (objInstance.getDataNascita() == null) {
			return false;

		}

		return true;
	}

	public void setAssicuratoDaoInstance(AssicuratoDaoImpl assicuratoDaoInstance) {
		this.assicuratoDaoInstance = assicuratoDaoInstance;
	}

	public void setNotProcessedDaoInstance(NotProcessedDaoImpl notProcessedDaoInstance) {
		this.notProcessedDaoInstance = notProcessedDaoInstance;
	}

	public void setCsvServiceInstance(CSVService csvServiceInstance) {
		this.csvServiceInstance = csvServiceInstance;
	}
	
}
