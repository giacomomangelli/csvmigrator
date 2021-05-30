package it.prova.datacsvmigrator.service;

import it.prova.datacsvmigrator.dao.assicurato.AssicuratoDaoImpl;
import it.prova.datacsvmigrator.dao.notprocessed.NotProcessedDaoImpl;
import it.prova.datacsvmigrator.flow.file.CSVService;

public class MyServiceFactory {
	
	private static DataMigrationService dataMigrationServiceInstance = null;
	private static CSVService csvServiceInstance = null;
	private static AssicuratoDaoImpl assicuratoDaoInstance = null;
	private static NotProcessedDaoImpl notProcessatoDaoInstance = null;
	
	
	public static DataMigrationService getMigrationServiceInstance() {
		if(dataMigrationServiceInstance==null) {
			dataMigrationServiceInstance = new DataMigrationService();
		}
		dataMigrationServiceInstance.setCsvServiceInstance(getCSVServiceInstance());
		dataMigrationServiceInstance.setAssicuratoDaoInstance(getAssicuratoDaoInstance());
		dataMigrationServiceInstance.setNotProcessedDaoInstance(getNotProcessedDaoInstance());
		return dataMigrationServiceInstance;
	}
	
	public static CSVService getCSVServiceInstance() {
		if(csvServiceInstance ==null) {
			csvServiceInstance = new CSVService();
		}
		return csvServiceInstance;
	}
	
	public static AssicuratoDaoImpl getAssicuratoDaoInstance() {
		if(assicuratoDaoInstance ==null) {
			assicuratoDaoInstance = new AssicuratoDaoImpl();
		}
		return assicuratoDaoInstance;
	}
	
	public static NotProcessedDaoImpl getNotProcessedDaoInstance() {
		if(notProcessatoDaoInstance ==null) {
			notProcessatoDaoInstance = new NotProcessedDaoImpl();
		}
		return notProcessatoDaoInstance;
	}
}
