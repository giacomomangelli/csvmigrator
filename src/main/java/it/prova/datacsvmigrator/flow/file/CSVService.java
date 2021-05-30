package it.prova.datacsvmigrator.flow.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.datacsvmigrator.model.CSVTransientObj;

public class CSVService {

	public List<CSVTransientObj> csvToJava(File csvFile) throws Exception {

		List<CSVTransientObj> objectsList = new ArrayList<>();

		try (InputStream streamCSV = new FileInputStream(csvFile);
				InputStreamReader input = new InputStreamReader(streamCSV)) {

			CSVFormat csvFormat = CSVFormat.newFormat(';').withFirstRecordAsHeader();
			CSVParser csvParser = csvFormat.parse(input);

			for (CSVRecord record : csvParser) {
				CSVTransientObj csvObj = new CSVTransientObj();
				csvObj.setId(numberValuesParser(record.get("id")));
				csvObj.setNome(record.get("nome"));
				csvObj.setCognome(record.get("cognome"));
				csvObj.setNumeroSinistri(numberValuesParser(record.get("numero_sinistri")).intValue());
				csvObj.setDataNascita(dateValueParser(record.get("data_nascita")));
				csvObj.setCodiceFiscale(record.get("codice_fiscale"));
				objectsList.add(csvObj);
			}

			return objectsList;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	private Long numberValuesParser(String numberInput) {

		Long value = null;

		if (NumberUtils.isCreatable(numberInput)) {
			value = Long.parseLong(numberInput);
		}

		return value;
	}

	private Date dateValueParser(String dateInput) {

		Date dataParsed = null;

		if (StringUtils.isBlank(dateInput))
			return dataParsed;

		try {

			dataParsed = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		return dataParsed;

	}

}
