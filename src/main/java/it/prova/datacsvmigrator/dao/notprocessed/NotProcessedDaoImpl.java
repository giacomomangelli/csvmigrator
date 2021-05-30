package it.prova.datacsvmigrator.dao.notprocessed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import it.prova.datacsvmigrator.dao.AbstractMySQLDAO;
import it.prova.datacsvmigrator.model.NotProcessed;

public class NotProcessedDaoImpl extends AbstractMySQLDAO {

	private Connection connection;

	public void insert(List<NotProcessed> notProcessedInstanceList) throws Exception {

		if (notProcessedInstanceList == null) {
			throw new Exception("Inserimento id invalido.");
		}

		if (notProcessedInstanceList.isEmpty()) {
			return;
		}

		if (!isNotActive()) {
			throw new Exception("Connessione chiusa. Impossibile operare con il Dao.");
		}

		String query = "INSERT INTO NOT_PROCESSED " + "(CODICE_FISCALE, OLD_ID)" + " VALUES (?,?)";

		try (PreparedStatement statement = connection.prepareStatement(query)) {

			for (NotProcessed notProcessedItem : notProcessedInstanceList) {
				statement.setString(1, notProcessedItem.getCodiceFiscale());
				statement.setString(2, notProcessedItem.getOldId());
			}
			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
