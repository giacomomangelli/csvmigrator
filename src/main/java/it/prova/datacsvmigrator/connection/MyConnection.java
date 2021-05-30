package it.prova.datacsvmigrator.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	public static Connection getConnection(String driver_name,String connect_str) throws Exception {

		Connection c = null;
		try {
			Class.forName(driver_name);
			c = DriverManager.getConnection(connect_str);
			return c;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

}
