package com.abctreinamentos.lojavirtual.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.abctreinamentos.lojavirtual.models.ClienteModel;

public class ClienteMapper {
	public static ClienteModel map(ResultSet resultSet) throws SQLException {
		ClienteModel clienteModel = new ClienteModel();
		
		clienteModel.setId(resultSet.getInt(1));
		clienteModel.setNome(resultSet.getString(2));
		clienteModel.setCpf(resultSet.getString(3));
		clienteModel.setEmail(resultSet.getString(4));
		
		return clienteModel;
	}
}
