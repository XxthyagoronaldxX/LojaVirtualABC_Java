package com.abctreinamentos.lojavirtual.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.abctreinamentos.lojavirtual.mapper.ClienteMapper;
import com.abctreinamentos.lojavirtual.models.ClienteModel;

public class ImplClienteDAO implements IClienteDAO {
	private Connection connection;

	public ImplClienteDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public ClienteModel create(ClienteModel data) {
		try {
			String sql = "INSERT INTO cliente(nome, cpf, email) VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, data.getNome());
			stmt.setString(2, data.getCpf());
			stmt.setString(3, data.getEmail());
			stmt.execute();

			return data;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<ClienteModel> findAll() {
		List<ClienteModel> clienteList = new ArrayList<>();

		try {
			String sql = "SELECT id, nome, cpf, email FROM cliente";
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next())
				clienteList.add(ClienteMapper.map(resultSet));

			return clienteList;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public ClienteModel remove(ClienteModel data) {
		try {
			String sql = "DELETE FROM cliente WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, data.getId());
			stmt.execute();

			return data;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public ClienteModel update(ClienteModel data) {
		try {
			String sql = "UPDATE cliente SET nome = ?, cpf = ?, email = ? WHERE cliente.id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, data.getNome());
			stmt.setString(2, data.getCpf());
			stmt.setString(3, data.getEmail());
			stmt.setInt(4, data.getId());
			stmt.execute();

			return data;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public ClienteModel findByCpf(String cpf) {
		try {
			String sql = "SELECT id, nome, cpf, email FROM cliente WHERE cliente.cpf = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, cpf);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next() == true) {
				ClienteModel cliente = ClienteMapper.map(resultSet);

				return cliente;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
