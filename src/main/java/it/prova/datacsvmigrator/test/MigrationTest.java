package it.prova.datacsvmigrator.test;

import java.io.File;

import it.prova.datacsvmigrator.service.DataMigrationService;
import it.prova.datacsvmigrator.service.MyServiceFactory;

public class MigrationTest {
	
	private static File fileInput = new File("C:/Users/Giacomo/Desktop/dati.csv");
	
	public static void main(String[] args) throws Exception {
		DataMigrationService service = MyServiceFactory.getMigrationServiceInstance(); 
		service.inserimentoAssicurati(fileInput);
	}

}
