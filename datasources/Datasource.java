package com.abctreinamentos.lojavirtual.datasources;

import java.sql.Connection;

public interface Datasource {
	public Connection createConnection();
}
