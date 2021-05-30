package it.prova.datacsvmigrator.dao.assicurato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import it.prova.datacsvmigrator.dao.AbstractMySQLDAO;
import it.prova.datacsvmigrator.model.Assicurato;

public class AssicuratoDaoImpl extends AbstractMySQLDAO {

	private Connection connection;

	public void insert(List<Assicurato> assicuratiInstance) throws Exception {

		if (assicuratiInstance == null) {
			throw new Exception("Inserimento assicurato invalido.");
		}
		
		if (assicuratiInstance.isEmpty()) {
			return;
		}

		if (!isNotActive()) {
			throw new Exception("Connessione chiusa. Impossibile operare con il Dao.");
		}

		String query = "INSERT INTO ASSICURATO " + "(NOME,COGNOME,DATA_NASCITA,NUMERO_SINISTRI,CODICE_FISCALE)"
				+ " VALUES (?,?,?,?,?)";

		try (PreparedStatement statement = connection.prepareStatement(query)) {
			for (Assicurato assicuratoItem : assicuratiInstance) {

				statement.setString(1, assicuratoItem.getNome());
				statement.setString(2, assicuratoItem.getCognome());

				if (assicuratoItem.getDataNascita() == null) {
					statement.setNull(3, java.sql.Types.DATE);
				} else {
					java.sql.Date date = new java.sql.Date(assicuratoItem.getDataNascita().getTime());
					statement.setDate(3, date);
				}
				
				statement.setInt(4, assicuratoItem.getNumeroSinistri());
				statement.setString(5, assicuratoItem.getCodiceFiscale());

				statement.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
