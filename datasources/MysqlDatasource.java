package com.abctreinamentos.lojavirtual.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDatasource implements Datasource {
	private static final String hostname = "localhost";
	private static final String port = "3306";
	private static final String url = "jdbc:mysql://%s:%s?verifyServerCertificate=false&useSSL=true".formatted(hostname, port);
	private static final String user = "root";
	private static final String password = "Tc20022002";
	private static final String db_name = "lojavirtual_db";
	
	public Connection createConnection() {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			
			connection.createStatement().execute("USE %s;".formatted(db_name));

			return connection;
		} catch (SQLException e) {
			return null;
		}
	}
}
